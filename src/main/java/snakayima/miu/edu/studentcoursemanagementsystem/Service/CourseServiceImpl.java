package snakayima.miu.edu.studentcoursemanagementsystem.Service;

import org.springframework.stereotype.Service;
import snakayima.miu.edu.studentcoursemanagementsystem.Exception.ResourceNotFoundException;
import snakayima.miu.edu.studentcoursemanagementsystem.Model.Course;
import snakayima.miu.edu.studentcoursemanagementsystem.Repository.CourseRepository;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", id));
    }

    @Override
    public Course updateCourse(Long id, Course courseDetails) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", id));

        course.setCourseName(courseDetails.getCourseName());
        course.setCredits(courseDetails.getCredits());

        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", id));
        courseRepository.delete(course);
    }
}