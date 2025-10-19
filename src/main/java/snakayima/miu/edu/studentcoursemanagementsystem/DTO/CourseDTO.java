package snakayima.miu.edu.studentcoursemanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Long courseId;
    private String courseName;
    private int credits;
    private Integer enrollmentCount;
}