package snakayima.miu.edu.studentcoursemanagementsystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import snakayima.miu.edu.studentcoursemanagementsystem.Model.Enrollment;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // Find all enrollments for a specific student
    List<Enrollment> findByStudentId(Long studentId);

    // Find all enrollments for a specific course
    // FIXED: Changed from findByCourseId to findByCourseCourseId
    List<Enrollment> findByCourseCourseId(Long courseId);

    // Check if a student is already enrolled in a course
    @Query("SELECT e FROM Enrollment e WHERE e.student.id = :studentId AND e.course.courseId = :courseId")
    Optional<Enrollment> findByStudentIdAndCourseId(
            @Param("studentId") Long studentId,
            @Param("courseId") Long courseId
    );

    // Count enrollments for a course
    // FIXED: Changed from countByCourseId to countByCourseCourseId
    long countByCourseCourseId(Long courseId);

    // Count enrollments for a student
    long countByStudentId(Long studentId);
}