// --- File: service/StudentService.java ---
package com.school.midland.userservice.service;

import com.school.midland.userservice.dto.StudentRequestDTO;
import com.school.midland.userservice.dto.StudentResponseDTO; // Import this
import com.school.midland.userservice.model.Section;
import com.school.midland.userservice.model.Student;
import com.school.midland.userservice.repository.SectionRepository;
import com.school.midland.userservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final SectionRepository sectionRepository;

    public StudentResponseDTO createStudent(StudentRequestDTO studentDTO) {
        Section section = sectionRepository.findById(studentDTO.sectionId())
                .orElseThrow(() -> new RuntimeException("Section not found with id: " + studentDTO.sectionId()));

        Student student = new Student();
        student.setId(UUID.randomUUID().toString());
        student.setName(studentDTO.name());
        student.setEmail(studentDTO.email());
        student.setSection(section);

        Student savedStudent = studentRepository.save(student);
        return mapToStudentResponseDTO(savedStudent);
    }

    public Student getStudentById(String id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }
    
    public StudentResponseDTO getStudentResponseById(String id) {
        Student student = getStudentById(id);
        return mapToStudentResponseDTO(student);
    }

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToStudentResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteStudent(String id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    private StudentResponseDTO mapToStudentResponseDTO(Student student) {
        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getSection().getId(),
                student.getSection().getName()
        );
    }
}