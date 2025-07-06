-- Insert Users
INSERT INTO users (uuid, username, password, role) VALUES
('teacher-001', 'john.doe', 'password123', 'TEACHER'),
('teacher-002', 'jane.smith', 'password123', 'TEACHER'),
('teacher-003', 'mike.wilson', 'password123', 'TEACHER'),
('student-001', 'alice.johnson', 'password123', 'STUDENT'),
('student-002', 'bob.brown', 'password123', 'STUDENT'),
('student-003', 'charlie.davis', 'password123', 'STUDENT'),
('student-004', 'diana.miller', 'password123', 'STUDENT'),
('student-005', 'eve.garcia', 'password123', 'STUDENT');

-- Insert Teachers
INSERT INTO teachers (tuuid, name, email, phone, subject, hire_date) VALUES
('teacher-001', 'John Doe', 'john.doe@school.com', '123-456-7890', 'Mathematics', '2020-06-15'),
('teacher-002', 'Jane Smith', 'jane.smith@school.com', '123-456-7891', 'English', '2019-08-20'),
('teacher-003', 'Mike Wilson', 'mike.wilson@school.com', '123-456-7892', 'Science', '2021-01-10');

-- Insert Subjects
INSERT INTO subjects (subject_name, subject_code, description) VALUES
('Mathematics', 'MATH101', 'Basic Mathematics for Grade 5'),
('English', 'ENG101', 'English Language and Literature'),
('Science', 'SCI101', 'General Science'),
('Social Studies', 'SS101', 'Social Studies and History'),
('Physical Education', 'PE101', 'Physical Education and Sports');

-- Insert Sections
INSERT INTO sections (grade, section_name, academic_year, class_teacher_uuid) VALUES
('5', 'A', '2024-25', 'teacher-001'),
('5', 'B', '2024-25', 'teacher-002'),
('6', 'A', '2024-25', 'teacher-003');

-- Insert Students
INSERT INTO students (suuid, name, roll_number, date_of_birth, email, phone, address, admission_date, section_id) VALUES
('student-001', 'Alice Johnson', '5A001', '2014-03-15', 'alice.parent@email.com', '987-654-3210', '123 Main St', '2024-04-01', 1),
('student-002', 'Bob Brown', '5A002', '2014-05-20', 'bob.parent@email.com', '987-654-3211', '456 Oak Ave', '2024-04-01', 1),
('student-003', 'Charlie Davis', '5A003', '2014-02-10', 'charlie.parent@email.com', '987-654-3212', '789 Pine Rd', '2024-04-01', 1),
('student-004', 'Diana Miller', '5B001', '2014-04-25', 'diana.parent@email.com', '987-654-3213', '321 Elm St', '2024-04-01', 2),
('student-005', 'Eve Garcia', '5B002', '2014-06-30', 'eve.parent@email.com', '987-654-3214', '654 Maple Dr', '2024-04-01', 2);

-- Insert Timetable (Monday schedule for Section 5-A)
INSERT INTO timetables (section_id, subject_id, teacher_uuid, day_of_week, start_time, end_time, classroom) VALUES
(1, 1, 'teacher-001', 'MONDAY', '09:00:00', '10:00:00', 'Room 101'),
(1, 2, 'teacher-002', 'MONDAY', '10:00:00', '11:00:00', 'Room 101'),
(1, 3, 'teacher-003', 'MONDAY', '11:30:00', '12:30:00', 'Room 101'),
(1, 1, 'teacher-001', 'TUESDAY', '09:00:00', '10:00:00', 'Room 101'),
(1, 2, 'teacher-002', 'TUESDAY', '10:00:00', '11:00:00', 'Room 101'),
(2, 1, 'teacher-001', 'MONDAY', '09:00:00', '10:00:00', 'Room 102'),
(2, 2, 'teacher-002', 'MONDAY', '10:00:00', '11:00:00', 'Room 102');

-- Insert Attendance Records (for current week)
-- Monday Math class for Section 5-A
INSERT INTO attendance (timetable_id, student_uuid, date, status, marked_by_teacher_uuid, timestamp_marked) VALUES
(1, 'student-001', '2024-07-01', 'PRESENT', 'teacher-001', '2024-07-01 09:05:00'),
(1, 'student-002', '2024-07-01', 'PRESENT', 'teacher-001', '2024-07-01 09:05:00'),
(1, 'student-003', '2024-07-01', 'ABSENT', 'teacher-001', '2024-07-01 09:05:00');

-- Monday English class for Section 5-A
INSERT INTO attendance (timetable_id, student_uuid, date, status, marked_by_teacher_uuid, timestamp_marked) VALUES
(2, 'student-001', '2024-07-01', 'PRESENT', 'teacher-002', '2024-07-01 10:05:00'),
(2, 'student-002', '2024-07-01', 'LATE', 'teacher-002', '2024-07-01 10:05:00'),
(2, 'student-003', '2024-07-01', 'ABSENT', 'teacher-002', '2024-07-01 10:05:00');

-- Tuesday Math class for Section 5-A
INSERT INTO attendance (timetable_id, student_uuid, date, status, marked_by_teacher_uuid, timestamp_marked) VALUES
(4, 'student-001', '2024-07-02', 'PRESENT', 'teacher-001', '2024-07-02 09:05:00'),
(4, 'student-002', '2024-07-02', 'PRESENT', 'teacher-001', '2024-07-02 09:05:00'),
(4, 'student-003', '2024-07-02', 'PRESENT', 'teacher-001', '2024-07-02 09:05:00');

-- Monday Math class for Section 5-B
INSERT INTO attendance (timetable_id, student_uuid, date, status, marked_by_teacher_uuid, timestamp_marked) VALUES
(6, 'student-004', '2024-07-01', 'PRESENT', 'teacher-001', '2024-07-01 09:05:00'),
(6, 'student-005', '2024-07-01', 'PRESENT', 'teacher-001', '2024-07-01 09:05:00');