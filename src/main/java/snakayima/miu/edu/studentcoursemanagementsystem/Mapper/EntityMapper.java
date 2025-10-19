package snakayima.miu.edu.studentcoursemanagementsystem.Mapper;

import snakayima.miu.edu.studentcoursemanagementsystem.DTO.CourseBasicDTO;
import snakayima.miu.edu.studentcoursemanagementsystem.DTO.CourseDTO;
import snakayima.miu.edu.studentcoursemanagementsystem.DTO.CourseWithEnrollmentsDTO;
import snakayima.miu.edu.studentcoursemanagementsystem.DTO.EnrollmentDTO;
import snakayima.miu.edu.studentcoursemanagementsystem.DTO.EnrollmentSummaryDTO;
import snakayima.miu.edu.studentcoursemanagementsystem.DTO.StudentBasicDTO;
import snakayima.miu.edu.studentcoursemanagementsystem.DTO.StudentDTO;
import snakayima.miu.edu.studentcoursemanagementsystem.DTO.StudentSummaryDTO;
import snakayima.miu.edu.studentcoursemanagementsystem.Model.Course;
import snakayima.miu.edu.studentcoursemanagementsystem.Model.Enrollment;
import snakayima.miu.edu.studentcoursemanagementsystem.Model.Student;

import java.util.stream.Collectors;

public class EntityMapper {

    // Student mappings
    public static StudentDTO toStudentDTO(Student student, boolean includeEnrollments) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());

        if (includeEnrollments && student.getEnrollments() != null) {
            dto.setEnrollments(
                    student.getEnrollments().stream()
                            .map(EntityMapper::toEnrollmentSummary)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    private static EnrollmentSummaryDTO toEnrollmentSummary(Enrollment enrollment) {
        return new EnrollmentSummaryDTO(
                enrollment.getEnrollmentId(),
                enrollment.getCourse().getCourseName(),
                enrollment.getCourse().getCredits(),
                enrollment.getEnrollmentDate().toString()
        );
    }

    // Course mappings
    public static CourseDTO toCourseDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setCourseId(course.getCourseId());
        dto.setCourseName(course.getCourseName());
        dto.setCredits(course.getCredits());
        dto.setEnrollmentCount(
                course.getEnrollments() != null ? course.getEnrollments().size() : 0
        );
        return dto;
    }

    public static CourseWithEnrollmentsDTO toCourseWithEnrollments(Course course) {
        CourseWithEnrollmentsDTO dto = new CourseWithEnrollmentsDTO();
        dto.setCourseId(course.getCourseId());
        dto.setCourseName(course.getCourseName());
        dto.setCredits(course.getCredits());

        if (course.getEnrollments() != null) {
            dto.setEnrolledStudents(
                    course.getEnrollments().stream()
                            .map(e -> new StudentSummaryDTO(
                                    e.getStudent().getId(),
                                    e.getStudent().getName(),
                                    e.getStudent().getEmail(),
                                    e.getEnrollmentDate().toString()
                            ))
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    // Enrollment mappings
    public static EnrollmentDTO toEnrollmentDTO(Enrollment enrollment) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setEnrollmentId(enrollment.getEnrollmentId());
        dto.setEnrollmentDate(enrollment.getEnrollmentDate());

        // Student info without enrollments (breaks circular reference)
        StudentBasicDTO studentDTO = new StudentBasicDTO();
        studentDTO.setId(enrollment.getStudent().getId());
        studentDTO.setName(enrollment.getStudent().getName());
        studentDTO.setEmail(enrollment.getStudent().getEmail());
        dto.setStudent(studentDTO);

        // Course info without enrollments (breaks circular reference)
        CourseBasicDTO courseDTO = new CourseBasicDTO();
        courseDTO.setCourseId(enrollment.getCourse().getCourseId());
        courseDTO.setCourseName(enrollment.getCourse().getCourseName());
        courseDTO.setCredits(enrollment.getCourse().getCredits());
        dto.setCourse(courseDTO);

        return dto;
    }
}