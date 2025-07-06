package com.studentmanagement.test02.service;

import com.studentmanagement.test02.dto.SectionDTO;
import com.studentmanagement.test02.dto.StudentDTO;
import com.studentmanagement.test02.model.Student;
import com.studentmanagement.test02.repo.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDTO> getAllStudents(){
        List<Student> students = studentRepository.findAll();

        return students.stream()
                .map(student -> new StudentDTO(
                        student.getSuuid(),
                        student.getName(),
                        student.getRollNumber(),
                        student.getDateOfBirth(),
                        student.getEmail(),
                        student.getPhone(),
                        student.getAddress(),
                        student.getAdmissionDate(),
                        new SectionDTO(
                                student.getSection().getSectionId(),
                                student.getSection().getGrade(),
                                student.getSection().getSectionName(),
                                student.getSection().getAcademicYear()
                        )
                ))
                .toList();
    }
}
