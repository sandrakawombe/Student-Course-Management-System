package snakayima.miu.edu.studentcoursemanagementsystem.Service;

import org.springframework.stereotype.Service;
import snakayima.miu.edu.studentcoursemanagementsystem.DTO.*;
import snakayima.miu.edu.studentcoursemanagementsystem.Exception.ResourceNotFoundException;
import snakayima.miu.edu.studentcoursemanagementsystem.Model.Course;
import snakayima.miu.edu.studentcoursemanagementsystem.Model.Enrollment;
import snakayima.miu.edu.studentcoursemanagementsystem.Model.Student;
import snakayima.miu.edu.studentcoursemanagementsystem.Repository.CourseRepository;
import snakayima.miu.edu.studentcoursemanagementsystem.Repository.EnrollmentRepository;
import snakayima.miu.edu.studentcoursemanagementsystem.Repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public AnalyticsService(StudentRepository studentRepository,
                           CourseRepository courseRepository,
                           EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public StudentGPADTO calculateStudentGPA(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", studentId));

        List<Enrollment> enrollments = student.getEnrollments();
        
        double totalPoints = 0.0;
        int totalCredits = 0;
        int completedCourses = 0;

        for (Enrollment e : enrollments) {
            if (e.getGrade() != null && 
                e.getGrade() != Enrollment.Grade.IN_PROGRESS &&
                e.getGrade() != Enrollment.Grade.INCOMPLETE &&
                e.getGrade() != Enrollment.Grade.WITHDRAWN) {
                
                totalPoints += e.getGrade().getGradePoint() * e.getCourse().getCredits();
                totalCredits += e.getCourse().getCredits();
                completedCourses++;
            }
        }

        double gpa = totalCredits > 0 ? totalPoints / totalCredits : 0.0;
        String academicStanding = determineAcademicStanding(gpa);

        return new StudentGPADTO(
            student.getId(),
            student.getName(),
            student.getEmail(),
            Math.round(gpa * 100.0) / 100.0,
            totalCredits,
            completedCourses,
            academicStanding
        );
    }

    public List<StudentGPADTO> getAllStudentsGPA() {
        return studentRepository.findAll().stream()
                .map(student -> calculateStudentGPA(student.getId()))
                .sorted(Comparator.comparing(StudentGPADTO::getGpa).reversed())
                .collect(Collectors.toList());
    }

    public CourseAnalyticsDTO getCourseAnalytics(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", courseId));

        List<Enrollment> enrollments = course.getEnrollments();
        
        int totalEnrolled = enrollments.size();
        int completed = 0;
        int dropped = 0;
        double totalGradePoints = 0.0;
        Map<String, Integer> gradeDistribution = new HashMap<>();

        for (Enrollment e : enrollments) {
            if (e.getGrade() != null) {
                String gradeLetter = e.getGrade().getLetter();
                gradeDistribution.put(gradeLetter, gradeDistribution.getOrDefault(gradeLetter, 0) + 1);

                if (e.getStatus() == Enrollment.EnrollmentStatus.COMPLETED) {
                    completed++;
                    totalGradePoints += e.getGrade().getGradePoint();
                } else if (e.getStatus() == Enrollment.EnrollmentStatus.DROPPED ||
                          e.getStatus() == Enrollment.EnrollmentStatus.WITHDRAWN) {
                    dropped++;
                }
            }
        }

        double averageGrade = completed > 0 ? totalGradePoints / completed : 0.0;
        int passRate = completed > 0 ? 
            (int) ((completed - gradeDistribution.getOrDefault("F", 0)) * 100.0 / completed) : 0;
        int dropRate = totalEnrolled > 0 ? (dropped * 100 / totalEnrolled) : 0;

        return new CourseAnalyticsDTO(
            course.getCourseId(),
            course.getCourseName(),
            totalEnrolled,
            Math.round(averageGrade * 100.0) / 100.0,
            gradeDistribution,
            passRate,
            dropRate
        );
    }

    public TranscriptDTO generateTranscript(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", studentId));

        StudentGPADTO gpaInfo = calculateStudentGPA(studentId);

        List<TranscriptCourseDTO> courses = student.getEnrollments().stream()
                .map(e -> new TranscriptCourseDTO(
                    e.getCourse().getCourseName(),
                    e.getCourse().getCredits(),
                    e.getGrade() != null ? e.getGrade().getLetter() : "IP",
                    e.getStatus() != null ? e.getStatus().name() : "ACTIVE",
                    e.getEnrollmentDate().toString()
                ))
                .collect(Collectors.toList());

        return new TranscriptDTO(
            student.getId(),
            student.getName(),
            student.getEmail(),
            gpaInfo.getGpa(),
            gpaInfo.getTotalCredits(),
            gpaInfo.getTotalCredits(),
            gpaInfo.getAcademicStanding(),
            courses
        );
    }

    public DashboardStatsDTO getDashboardStats() {
        List<StudentGPADTO> allGPAs = getAllStudentsGPA();
        
        double avgGPA = allGPAs.stream()
                .mapToDouble(StudentGPADTO::getGpa)
                .average()
                .orElse(0.0);

        long deansList = allGPAs.stream()
                .filter(s -> s.getGpa() >= 3.5)
                .count();

        long probation = allGPAs.stream()
                .filter(s -> s.getGpa() < 2.0 && s.getGpa() > 0)
                .count();

        List<Enrollment> allEnrollments = enrollmentRepository.findAll();
        long activeEnrollments = allEnrollments.stream()
                .filter(e -> e.getStatus() == Enrollment.EnrollmentStatus.ACTIVE)
                .count();

        return new DashboardStatsDTO(
            (int) studentRepository.count(),
            (int) courseRepository.count(),
            allEnrollments.size(),
            (int) activeEnrollments,
            Math.round(avgGPA * 100.0) / 100.0,
            (int) deansList,
            (int) probation
        );
    }

    private String determineAcademicStanding(double gpa) {
        if (gpa >= 3.8) return "Dean's List (High Honors)";
        if (gpa >= 3.5) return "Dean's List";
        if (gpa >= 3.0) return "Good Standing";
        if (gpa >= 2.5) return "Satisfactory";
        if (gpa >= 2.0) return "Warning";
        if (gpa > 0) return "Academic Probation";
        return "No Grades Yet";
    }
}