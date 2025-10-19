package snakayima.miu.edu.studentcoursemanagementsystem.Exception;

public class DuplicateEnrollmentException extends RuntimeException {
    public DuplicateEnrollmentException(String message) {
        super(message);
    }
    
    public DuplicateEnrollmentException(Long studentId, Long courseId) {
        super(String.format("Student with id %d is already enrolled in course with id %d", 
                          studentId, courseId));
    }
}
