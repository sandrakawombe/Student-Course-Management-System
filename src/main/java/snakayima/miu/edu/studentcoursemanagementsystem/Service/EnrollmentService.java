package snakayima.miu.edu.studentcoursemanagementsystem.Service;

import snakayima.miu.edu.studentcoursemanagementsystem.Model.Enrollment;

import java.util.List;

public interface EnrollmentService {

    List<Enrollment> getAllEnrollments();
    Enrollment addEnrollment(Enrollment enrollment);
}
