package snakayima.miu.edu.studentcoursemanagementsystem.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import snakayima.miu.edu.studentcoursemanagementsystem.DTO.EnrollmentDTO;
import snakayima.miu.edu.studentcoursemanagementsystem.Mapper.EntityMapper;
import snakayima.miu.edu.studentcoursemanagementsystem.Model.Enrollment;
import snakayima.miu.edu.studentcoursemanagementsystem.Service.EnrollmentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> getAllEnrollments() {
        List<EnrollmentDTO> enrollments = enrollmentService.getAllEnrollments().stream()
                .map(EntityMapper::toEnrollmentDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(enrollments);
    }

    @PostMapping
    public ResponseEntity<EnrollmentDTO> addEnrollment(@RequestBody Enrollment enrollment) {
        Enrollment savedEnrollment = enrollmentService.addEnrollment(enrollment);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(EntityMapper.toEnrollmentDTO(savedEnrollment));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByStudent(@PathVariable Long studentId) {
        List<EnrollmentDTO> enrollments = enrollmentService.getEnrollmentsByStudentId(studentId).stream()
                .map(EntityMapper::toEnrollmentDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByCourse(@PathVariable Long courseId) {
        List<EnrollmentDTO> enrollments = enrollmentService.getEnrollmentsByCourseId(courseId).stream()
                .map(EntityMapper::toEnrollmentDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(enrollments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }
}