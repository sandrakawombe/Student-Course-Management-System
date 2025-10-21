package snakayima.miu.edu.studentcoursemanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranscriptCourseDTO {
    private String courseName;
    private Integer credits;
    private String grade;
    private String status;
    private String enrollmentDate;
}