package snakayima.miu.edu.studentcoursemanagementsystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import snakayima.miu.edu.studentcoursemanagementsystem.Model.Enrollment;
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
