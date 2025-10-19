package snakayima.miu.edu.studentcoursemanagementsystem.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import snakayima.miu.edu.studentcoursemanagementsystem.DTO.CourseDTO;
import snakayima.miu.edu.studentcoursemanagementsystem.DTO.CourseWithEnrollmentsDTO;
import snakayima.miu.edu.studentcoursemanagementsystem.Mapper.EntityMapper;
import snakayima.miu.edu.studentcoursemanagementsystem.Model.Course;
import snakayima.miu.edu.studentcoursemanagementsystem.Service.CourseService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses().stream()
                .map(EntityMapper::toCourseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(courses);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> addCourse(@RequestBody Course course) {
        Course savedCourse = courseService.addCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(EntityMapper.toCourseDTO(savedCourse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseWithEnrollmentsDTO> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(EntityMapper.toCourseWithEnrollments(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(
            @PathVariable Long id,
            @RequestBody Course course) {

        Course updated = courseService.updateCourse(id, course);
        return ResponseEntity.ok(EntityMapper.toCourseDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}