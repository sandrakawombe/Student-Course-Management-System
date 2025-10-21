package snakayima.miu.edu.studentcoursemanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseAnalyticsDTO {
    private Long courseId;
    private String courseName;
    private Integer totalEnrolled;
    private Double averageGrade;
    private Map<String, Integer> gradeDistribution;
    private Integer passRate;
    private Integer dropRate;
}