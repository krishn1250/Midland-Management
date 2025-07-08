// --- File: service/StudentService.java ---
package com.school.midland.userservice.service;

import com.school.midland.userservice.dto.StudentRequestDTO;
import com.school.midland.userservice.model.Section;
import com.school.midland.userservice.model.Student;
import com.school.midland.userservice.repository.SectionRepository;
import com.school.midland.userservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID; // Good for generating IDs

@Service
@RequiredArgsConstructor // Injects dependencies via constructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final SectionRepository sectionRepository; // We need this to link a student to a section

    public Student createStudent(StudentRequestDTO studentDTO) {
        // Find the section the student will belong to
        Section section = sectionRepository.findById(studentDTO.sectionId())
                .orElseThrow(() -> new RuntimeException("Section not found with id: " + studentDTO.sectionId()));

        // Convert the DTO to an Entity
        Student student = new Student();
        student.setId(UUID.randomUUID().toString()); // Generate a unique ID
        student.setName(studentDTO.name());
        student.setEmail(studentDTO.email());
        student.setSection(section);

        // Save the new student to the database
        return studentRepository.save(student);
    }

    public Student getStudentById(String id) {
         return studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudent(String id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

}