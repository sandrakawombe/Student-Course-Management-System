package snakayima.miu.edu.studentcoursemanagementsystem.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import snakayima.miu.edu.studentcoursemanagementsystem.DTO.*;
import snakayima.miu.edu.studentcoursemanagementsystem.Service.AnalyticsService;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<DashboardStatsDTO> getDashboardStats() {
        return ResponseEntity.ok(analyticsService.getDashboardStats());
    }

    @GetMapping("/students/gpa")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<List<StudentGPADTO>> getAllStudentsGPA() {
        return ResponseEntity.ok(analyticsService.getAllStudentsGPA());
    }

    @GetMapping("/students/{studentId}/gpa")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    public ResponseEntity<StudentGPADTO> getStudentGPA(@PathVariable Long studentId) {
        return ResponseEntity.ok(analyticsService.calculateStudentGPA(studentId));
    }

    @GetMapping("/students/{studentId}/transcript")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    public ResponseEntity<TranscriptDTO> getTranscript(@PathVariable Long studentId) {
        return ResponseEntity.ok(analyticsService.generateTranscript(studentId));
    }

    @GetMapping("/courses/{courseId}/analytics")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<CourseAnalyticsDTO> getCourseAnalytics(@PathVariable Long courseId) {
        return ResponseEntity.ok(analyticsService.getCourseAnalytics(courseId));
    }

    @GetMapping("/deans-list")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<List<StudentGPADTO>> getDeansList() {
        List<StudentGPADTO> deansList = analyticsService.getAllStudentsGPA().stream()
                .filter(s -> s.getGpa() >= 3.5)
                .toList();
        return ResponseEntity.ok(deansList);
    }

    @GetMapping("/probation")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<StudentGPADTO>> getProbationList() {
        List<StudentGPADTO> probation = analyticsService.getAllStudentsGPA().stream()
                .filter(s -> s.getGpa() < 2.0 && s.getGpa() > 0)
                .toList();
        return ResponseEntity.ok(probation);
    }
}