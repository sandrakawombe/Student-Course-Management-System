package snakayima.miu.edu.studentcoursemanagementsystem.Service;

import snakayima.miu.edu.studentcoursemanagementsystem.Model.Enrollment;

import java.util.List;

public interface EnrollmentService {
    List<Enrollment> getAllEnrollments();
    Enrollment addEnrollment(Enrollment enrollment);
    List<Enrollment> getEnrollmentsByStudentId(Long studentId);
    List<Enrollment> getEnrollmentsByCourseId(Long courseId);
    void deleteEnrollment(Long id);
    // ========== Add to EnrollmentService.java interface ==========
    Enrollment getEnrollmentById(Long id);
    Enrollment updateEnrollment(Enrollment enrollment);
}