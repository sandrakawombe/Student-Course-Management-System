package snakayima.miu.edu.studentcoursemanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranscriptDTO {
    private Long studentId;
    private String studentName;
    private String email;
    private Double cumulativeGPA;
    private Integer totalCreditsAttempted;
    private Integer totalCreditsEarned;
    private String academicStanding;
    private List<TranscriptCourseDTO> courses;
}