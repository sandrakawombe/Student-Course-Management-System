package snakayima.miu.edu.studentcoursemanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDTO {
    private Long enrollmentId;
    private LocalDate enrollmentDate;
    private StudentBasicDTO student;
    private CourseBasicDTO course;
}