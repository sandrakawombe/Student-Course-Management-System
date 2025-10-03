package snakayima.miu.edu.studentcoursemanagementsystem.Service;

import snakayima.miu.edu.studentcoursemanagementsystem.Model.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    Course addCourse(Course course);
    Course getCourseById(Long id);
}
