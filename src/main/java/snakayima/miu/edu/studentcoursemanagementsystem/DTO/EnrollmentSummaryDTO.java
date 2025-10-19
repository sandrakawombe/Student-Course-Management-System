package snakayima.miu.edu.studentcoursemanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentSummaryDTO {
    private Long enrollmentId;
    private String courseName;
    private int credits;
    private String enrollmentDate;
}