-- Insert 10 Students
INSERT INTO student (name, email) VALUES
                                      ('Alice Johnson', 'alice.johnson@school.edu'),
                                      ('Bob Smith', 'bob.smith@school.edu'),
                                      ('Carol White', 'carol.white@school.edu'),
                                      ('David Brown', 'david.brown@school.edu'),
                                      ('Emma Davis', 'emma.davis@school.edu'),
                                      ('Frank Miller', 'frank.miller@school.edu'),
                                      ('Grace Wilson', 'grace.wilson@school.edu'),
                                      ('Henry Moore', 'henry.moore@school.edu'),
                                      ('Ivy Taylor', 'ivy.taylor@school.edu'),
                                      ('Jack Anderson', 'jack.anderson@school.edu');

-- Insert 10 Courses
INSERT INTO course (course_name, credits) VALUES
                                              ('Introduction to Computer Science', 3),
                                              ('Data Structures and Algorithms', 4),
                                              ('Database Management Systems', 3),
                                              ('Web Development', 3),
                                              ('Software Engineering', 4),
                                              ('Machine Learning', 4),
                                              ('Operating Systems', 3),
                                              ('Computer Networks', 3),
                                              ('Mobile App Development', 3),
                                              ('Cybersecurity Fundamentals', 3);

-- Insert 10 Users (password: password123)
INSERT INTO users (username, password, email, role, enabled) VALUES
                                                                 ('admin1', '$2a$10$8K1p/WKRQvEqVqVqEQVqceXQRhpQqBpILXKYCqYKqBxvQwZmNXJHe', 'admin@school.edu', 'ADMIN', true),
                                                                 ('teacher1', '$2a$10$8K1p/WKRQvEqVqVqEQVqceXQRhpQqBpILXKYCqYKqBxvQwZmNXJHe', 'teacher1@school.edu', 'TEACHER', true),
                                                                 ('teacher2', '$2a$10$8K1p/WKRQvEqVqVqEQVqceXQRhpQqBpILXKYCqYKqBxvQwZmNXJHe', 'teacher2@school.edu', 'TEACHER', true),
                                                                 ('alice_user', '$2a$10$8K1p/WKRQvEqVqVqEQVqceXQRhpQqBpILXKYCqYKqBxvQwZmNXJHe', 'alice.johnson@school.edu', 'STUDENT', true),
                                                                 ('bob_user', '$2a$10$8K1p/WKRQvEqVqVqEQVqceXQRhpQqBpILXKYCqYKqBxvQwZmNXJHe', 'bob.smith@school.edu', 'STUDENT', true),
                                                                 ('carol_user', '$2a$10$8K1p/WKRQvEqVqVqEQVqceXQRhpQqBpILXKYCqYKqBxvQwZmNXJHe', 'carol.white@school.edu', 'STUDENT', true),
                                                                 ('david_user', '$2a$10$8K1p/WKRQvEqVqVqEQVqceXQRhpQqBpILXKYCqYKqBxvQwZmNXJHe', 'david.brown@school.edu', 'STUDENT', true),
                                                                 ('emma_user', '$2a$10$8K1p/WKRQvEqVqVqEQVqceXQRhpQqBpILXKYCqYKqBxvQwZmNXJHe', 'emma.davis@school.edu', 'STUDENT', true),
                                                                 ('frank_user', '$2a$10$8K1p/WKRQvEqVqVqEQVqceXQRhpQqBpILXKYCqYKqBxvQwZmNXJHe', 'frank.miller@school.edu', 'STUDENT', true),
                                                                 ('grace_user', '$2a$10$8K1p/WKRQvEqVqVqEQVqceXQRhpQqBpILXKYCqYKqBxvQwZmNXJHe', 'grace.wilson@school.edu', 'STUDENT', true);

-- Insert 20 Enrollments with grades and status
INSERT INTO enrollment (enrollment_date, student_id, course_id, grade, status) VALUES
-- Alice (Excellent student - Dean's List)
('2024-09-01', 1, 1, 'A', 'COMPLETED'),
('2024-09-01', 1, 2, 'A_MINUS', 'COMPLETED'),
('2024-09-01', 1, 3, 'A_PLUS', 'COMPLETED'),
('2025-01-15', 1, 4, 'IN_PROGRESS', 'ACTIVE'),

-- Bob (Good student)
('2024-09-01', 2, 1, 'B_PLUS', 'COMPLETED'),
('2024-09-01', 2, 3, 'B', 'COMPLETED'),
('2025-01-15', 2, 5, 'IN_PROGRESS', 'ACTIVE'),

-- Carol (Average student)
('2024-09-01', 3, 2, 'C_PLUS', 'COMPLETED'),
('2024-09-01', 3, 4, 'B_MINUS', 'COMPLETED'),
('2025-01-15', 3, 6, 'IN_PROGRESS', 'ACTIVE'),

-- David (Struggling student - Probation)
('2024-09-01', 4, 1, 'D', 'COMPLETED'),
('2024-09-01', 4, 5, 'C', 'COMPLETED'),
('2025-01-15', 4, 7, 'IN_PROGRESS', 'ACTIVE'),

-- Emma (Excellent - Dean's List)
('2024-09-01', 5, 3, 'A', 'COMPLETED'),
('2024-09-01', 5, 6, 'A_PLUS', 'COMPLETED'),
('2025-01-15', 5, 8, 'IN_PROGRESS', 'ACTIVE'),

-- Frank (Dropped a course)
('2024-09-01', 6, 2, 'B', 'COMPLETED'),
('2024-09-15', 6, 7, 'WITHDRAWN', 'DROPPED'),

-- Grace (Good student)
('2024-09-01', 7, 4, 'B_PLUS', 'COMPLETED'),
('2025-01-15', 7, 9, 'IN_PROGRESS', 'ACTIVE'),

-- Henry (New student - no grades yet)
('2025-01-15', 8, 1, 'IN_PROGRESS', 'ACTIVE');