package snakayima.miu.edu.studentcoursemanagementsystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import snakayima.miu.edu.studentcoursemanagementsystem.Model.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
