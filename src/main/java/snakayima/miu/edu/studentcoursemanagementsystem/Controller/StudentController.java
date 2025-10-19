package snakayima.miu.edu.studentcoursemanagementsystem.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import snakayima.miu.edu.studentcoursemanagementsystem.DTO.StudentDTO;
import snakayima.miu.edu.studentcoursemanagementsystem.Mapper.EntityMapper;
import snakayima.miu.edu.studentcoursemanagementsystem.Model.Student;
import snakayima.miu.edu.studentcoursemanagementsystem.Service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * GET /students
     * Optional query param: includeEnrollments (default: false)
     * Example: GET /students?includeEnrollments=true
     */
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents(
            @RequestParam(defaultValue = "false") boolean includeEnrollments) {

        List<StudentDTO> students = studentService.getAllStudents().stream()
                .map(s -> EntityMapper.toStudentDTO(s, includeEnrollments))
                .collect(Collectors.toList());

        return ResponseEntity.ok(students);
    }

    /**
     * POST /students
     * Body: {"name": "John Doe", "email": "john@example.com"}
     * Returns: StudentDTO (without enrollments)
     */
    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@RequestBody Student student) {
        Student savedStudent = studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(EntityMapper.toStudentDTO(savedStudent, false));
    }

    /**
     * GET /students/{id}
     * Optional query param: includeEnrollments (default: true)
     * Example: GET /students/1?includeEnrollments=true
     */
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(
            @PathVariable Long id,
            @RequestParam(defaultValue = "true") boolean includeEnrollments) {

        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(EntityMapper.toStudentDTO(student, includeEnrollments));
    }

    /**
     * PUT /students/{id}
     * Body: {"name": "Updated Name", "email": "updated@example.com"}
     * Returns: StudentDTO (without enrollments)
     */
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(
            @PathVariable Long id,
            @RequestBody Student student) {

        Student updated = studentService.updateStudent(id, student);
        return ResponseEntity.ok(EntityMapper.toStudentDTO(updated, false));
    }

    /**
     * DELETE /students/{id}
     * Returns: 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}