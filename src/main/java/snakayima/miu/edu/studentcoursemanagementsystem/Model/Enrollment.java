package snakayima.miu.edu.studentcoursemanagementsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    @NotNull(message = "Enrollment date must not be null")
    private LocalDate enrollmentDate;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status = EnrollmentStatus.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public enum Grade {
        A_PLUS(4.0, "A+"),
        A(4.0, "A"),
        A_MINUS(3.7, "A-"),
        B_PLUS(3.3, "B+"),
        B(3.0, "B"),
        B_MINUS(2.7, "B-"),
        C_PLUS(2.3, "C+"),
        C(2.0, "C"),
        C_MINUS(1.7, "C-"),
        D_PLUS(1.3, "D+"),
        D(1.0, "D"),
        F(0.0, "F"),
        INCOMPLETE(0.0, "I"),
        WITHDRAWN(0.0, "W"),
        IN_PROGRESS(0.0, "IP");

        private final double gradePoint;
        private final String letter;

        Grade(double gradePoint, String letter) {
            this.gradePoint = gradePoint;
            this.letter = letter;
        }

        public double getGradePoint() {
            return gradePoint;
        }

        public String getLetter() {
            return letter;
        }
    }

    public enum EnrollmentStatus {
        ACTIVE,
        COMPLETED,
        DROPPED,
        WITHDRAWN
    }
}