package snakayima.miu.edu.studentcoursemanagementsystem.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    // ✅ Return DTO list to avoid recursion
    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> getAllEnrollments() {
        List<EnrollmentDTO> enrollments = enrollmentService.getAllEnrollments()
                .stream()
                .map(EntityMapper::toEnrollmentDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(enrollments);
    }

    // ✅ Return DTO to avoid nested entity serialization
    @PostMapping
    public ResponseEntity<EnrollmentDTO> addEnrollment(@RequestBody Enrollment enrollment) {
        Enrollment savedEnrollment = enrollmentService.addEnrollment(enrollment);
        EnrollmentDTO dto = EntityMapper.toEnrollmentDTO(savedEnrollment);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByStudent(@PathVariable Long studentId) {
        List<EnrollmentDTO> enrollments = enrollmentService.getEnrollmentsByStudentId(studentId)
                .stream()
                .map(EntityMapper::toEnrollmentDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByCourse(@PathVariable Long courseId) {
        List<EnrollmentDTO> enrollments = enrollmentService.getEnrollmentsByCourseId(courseId)
                .stream()
                .map(EntityMapper::toEnrollmentDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(enrollments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{enrollmentId}/grade")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<EnrollmentDTO> updateGrade(
            @PathVariable Long enrollmentId,
            @RequestParam String grade) {

        Enrollment enrollment = enrollmentService.getEnrollmentById(enrollmentId);
        try {
            enrollment.setGrade(Enrollment.Grade.valueOf(grade.toUpperCase()));
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Invalid grade value: " + grade);
        }
        enrollment.setStatus(Enrollment.EnrollmentStatus.COMPLETED);

        Enrollment updated = enrollmentService.updateEnrollment(enrollment);
        return ResponseEntity.ok(EntityMapper.toEnrollmentDTO(updated));
    }

    @PutMapping("/{enrollmentId}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    public ResponseEntity<EnrollmentDTO> updateStatus(
            @PathVariable Long enrollmentId,
            @RequestParam String status) {

        Enrollment enrollment = enrollmentService.getEnrollmentById(enrollmentId);
        enrollment.setStatus(Enrollment.EnrollmentStatus.valueOf(status));

        Enrollment updated = enrollmentService.updateEnrollment(enrollment);
        return ResponseEntity.ok(EntityMapper.toEnrollmentDTO(updated));
    }
}
