package snakayima.miu.edu.studentcoursemanagementsystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import snakayima.miu.edu.studentcoursemanagementsystem.Model.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    // JPQL query
    @Query("SELECT c FROM Course c WHERE c.courseId = :course_id")
    List<Course> getAllCourseById(@Param("course_id") Long id);

    // Derived query method - Spring Data JPA will automatically implement this
    // Matches the 'courseName' property in Course entity
    List<Course> findByCourseName(String courseName);

    // Or if you prefer native query (both work)
    @Query(value = "SELECT * FROM course WHERE course_name = ?1", nativeQuery = true)
    List<Course> findCoursesByNameNative(String courseName);
}