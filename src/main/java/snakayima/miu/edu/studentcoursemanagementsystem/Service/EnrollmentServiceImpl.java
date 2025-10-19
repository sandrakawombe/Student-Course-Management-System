package snakayima.miu.edu.studentcoursemanagementsystem.Service;

import org.springframework.stereotype.Service;
import snakayima.miu.edu.studentcoursemanagementsystem.Exception.DuplicateEnrollmentException;
import snakayima.miu.edu.studentcoursemanagementsystem.Exception.ResourceNotFoundException;
import snakayima.miu.edu.studentcoursemanagementsystem.Model.Course;
import snakayima.miu.edu.studentcoursemanagementsystem.Model.Enrollment;
import snakayima.miu.edu.studentcoursemanagementsystem.Model.Student;
import snakayima.miu.edu.studentcoursemanagementsystem.Repository.CourseRepository;
import snakayima.miu.edu.studentcoursemanagementsystem.Repository.EnrollmentRepository;
import snakayima.miu.edu.studentcoursemanagementsystem.Repository.StudentRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository,
                                 StudentRepository studentRepository,
                                 CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment addEnrollment(Enrollment enrollment) {
        // Verify student exists
        Student student = studentRepository.findById(enrollment.getStudent().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Student",
                        enrollment.getStudent().getId()));

        // Verify course exists
        Course course = courseRepository.findById(enrollment.getCourse().getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course",
                        enrollment.getCourse().getCourseId()));

        // Check for duplicate enrollment
        enrollmentRepository.findByStudentIdAndCourseId(student.getId(), course.getCourseId())
                .ifPresent(e -> {
                    throw new DuplicateEnrollmentException(student.getId(), course.getCourseId());
                });

        // Set enrollment date if not provided
        if (enrollment.getEnrollmentDate() == null) {
            enrollment.setEnrollmentDate(LocalDate.now());
        }

        enrollment.setStudent(student);
        enrollment.setCourse(course);

        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudentId(Long studentId) {
        // Verify student exists
        studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", studentId));

        return enrollmentRepository.findByStudentId(studentId);
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourseId(Long courseId) {
        // Verify course exists
        courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", courseId));

        // FIXED: Use the corrected method name
        return enrollmentRepository.findByCourseCourseId(courseId);
    }

    @Override
    public void deleteEnrollment(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment", id));
        enrollmentRepository.delete(enrollment);
    }
}