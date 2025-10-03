package snakayima.miu.edu.studentcoursemanagementsystem.Service;

import snakayima.miu.edu.studentcoursemanagementsystem.Model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student addStudent(Student student);
    Student getStudentById(Long id);
}
