package snakayima.miu.edu.studentcoursemanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentGPADTO {
    private Long studentId;
    private String studentName;
    private String email;
    private Double gpa;
    private Integer totalCredits;
    private Integer completedCourses;
    private String academicStanding;
}