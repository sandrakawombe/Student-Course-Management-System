package snakayima.miu.edu.studentcoursemanagementsystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import snakayima.miu.edu.studentcoursemanagementsystem.Model.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    //JPQL
    @Query(value ="select c from Course c where c.courseId=:course_id")
    List<Course> getAllCourseById(@Param("course_id") Integer id);

    //Native Query
    List<Course> getAllCoursesByName(String name);
}
