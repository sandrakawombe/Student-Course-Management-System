package snakayima.miu.edu.studentcoursemanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardStatsDTO {
    private Integer totalStudents;
    private Integer totalCourses;
    private Integer totalEnrollments;
    private Integer activeEnrollments;
    private Double averageGPA;
    private Integer studentsOnDeansList;
    private Integer studentsOnProbation;
}