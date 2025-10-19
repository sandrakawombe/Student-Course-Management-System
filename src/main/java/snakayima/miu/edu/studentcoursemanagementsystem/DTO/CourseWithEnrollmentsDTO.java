package snakayima.miu.edu.studentcoursemanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseWithEnrollmentsDTO {
    private Long courseId;
    private String courseName;
    private int credits;
    private List<StudentSummaryDTO> enrolledStudents;
}