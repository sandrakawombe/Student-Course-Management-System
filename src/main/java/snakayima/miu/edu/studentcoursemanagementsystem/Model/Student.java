package snakayima.miu.edu.studentcoursemanagementsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Student name must not be blank")
    private String name;
    @NotBlank(message = "Student email must not be blank")
    private String email;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;
}