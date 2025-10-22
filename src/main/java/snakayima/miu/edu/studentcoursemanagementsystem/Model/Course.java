package snakayima.miu.edu.studentcoursemanagementsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    @NotBlank(message = "Course name must not be blank")
    private String courseName;
    @NotNull(message = "Credits must not be null")
    private int credits;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;
}