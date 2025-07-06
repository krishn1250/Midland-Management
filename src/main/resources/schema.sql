-- Drop tables if they exist (for clean restart)
DROP TABLE IF EXISTS attendance;
DROP TABLE IF EXISTS timetables;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS teachers;
DROP TABLE IF EXISTS subjects;
DROP TABLE IF EXISTS sections;
DROP TABLE IF EXISTS users;

-- Create Users table
CREATE TABLE users (
                       uuid VARCHAR(255) PRIMARY KEY,
                       username VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL CHECK (role IN ('TEACHER', 'STUDENT'))
);

-- Create Sections table
CREATE TABLE sections (
                          section_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          grade VARCHAR(10) NOT NULL,
                          section_name VARCHAR(10) NOT NULL,
                          academic_year VARCHAR(20) NOT NULL,
                          class_teacher_uuid VARCHAR(255),
                          FOREIGN KEY (class_teacher_uuid) REFERENCES users(uuid)
);

-- Create Teachers table
CREATE TABLE teachers (
                          tuuid VARCHAR(255) PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) UNIQUE,
                          phone VARCHAR(20),
                          subject VARCHAR(255),
                          hire_date DATE,
                          FOREIGN KEY (tuuid) REFERENCES users(uuid)
);

-- Create Students table
CREATE TABLE students (
                          suuid VARCHAR(255) PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          roll_number VARCHAR(50) UNIQUE,
                          date_of_birth DATE,
                          email VARCHAR(255),
                          phone VARCHAR(20),
                          address TEXT,
                          admission_date DATE,
                          section_id BIGINT,
                          FOREIGN KEY (suuid) REFERENCES users(uuid),
                          FOREIGN KEY (section_id) REFERENCES sections(section_id)
);

-- Create Subjects table
CREATE TABLE subjects (
                          subject_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          subject_name VARCHAR(255) UNIQUE NOT NULL,
                          subject_code VARCHAR(50) UNIQUE,
                          description TEXT
);

-- Create Timetables table
CREATE TABLE timetables (
                            timetable_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            section_id BIGINT NOT NULL,
                            subject_id BIGINT NOT NULL,
                            teacher_uuid VARCHAR(255),
                            day_of_week VARCHAR(20) NOT NULL CHECK (day_of_week IN ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY')),
                            start_time TIME NOT NULL,
                            end_time TIME NOT NULL,
                            classroom VARCHAR(100),
                            FOREIGN KEY (section_id) REFERENCES sections(section_id),
                            FOREIGN KEY (subject_id) REFERENCES subjects(subject_id),
                            FOREIGN KEY (teacher_uuid) REFERENCES teachers(tuuid)
);

-- Create Attendance table
CREATE TABLE attendance (
                            attendance_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            timetable_id BIGINT NOT NULL,
                            student_uuid VARCHAR(255) NOT NULL,
                            date DATE NOT NULL,
                            status VARCHAR(20) NOT NULL CHECK (status IN ('PRESENT', 'ABSENT', 'LATE', 'EXCUSED')),
                            marked_by_teacher_uuid VARCHAR(255),
                            timestamp_marked TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            remarks TEXT,
                            FOREIGN KEY (timetable_id) REFERENCES timetables(timetable_id),
                            FOREIGN KEY (student_uuid) REFERENCES students(suuid),
                            FOREIGN KEY (marked_by_teacher_uuid) REFERENCES teachers(tuuid),
                            UNIQUE(timetable_id, student_uuid, date)
);

-- Create indexes for better performance
CREATE INDEX idx_students_section_id ON students(section_id);
CREATE INDEX idx_students_roll_number ON students(roll_number);
CREATE INDEX idx_timetables_section_day ON timetables(section_id, day_of_week);
CREATE INDEX idx_attendance_date ON attendance(date);
CREATE INDEX idx_attendance_student_date ON attendance(student_uuid, date);