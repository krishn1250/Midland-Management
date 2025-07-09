// --- File: service/TeacherService.java ---
package com.school.midland.userservice.service;

import com.school.midland.userservice.dto.TeacherRequestDTO;
import com.school.midland.userservice.dto.TeacherResponseDTO;
import com.school.midland.userservice.model.Section;
import com.school.midland.userservice.model.Teacher;
import com.school.midland.userservice.repository.SectionRepository;
import com.school.midland.userservice.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final SectionRepository sectionRepository; // Need this to find sections

    @Transactional
    public TeacherResponseDTO createTeacher(TeacherRequestDTO dto) {
        Teacher teacher = new Teacher();
        teacher.setId(UUID.randomUUID().toString());
        teacher.setName(dto.name());
        teacher.setEmail(dto.email());

        // Find and associate sections
        if (dto.sectionIds() != null && !dto.sectionIds().isEmpty()) {
            List<Section> sections = sectionRepository.findAllById(dto.sectionIds());
            teacher.setSections(sections);
        } else {
            teacher.setSections(Collections.emptyList());
        }

        Teacher savedTeacher = teacherRepository.save(teacher);
        return mapToTeacherResponseDTO(savedTeacher);
    }

    public Teacher getTeacherById(String teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));
    }
    
    public TeacherResponseDTO getTeacherResponseById(String teacherId) {
        Teacher teacher = getTeacherById(teacherId);
        return mapToTeacherResponseDTO(teacher);
    }

    public List<TeacherResponseDTO> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(this::mapToTeacherResponseDTO)
                .collect(Collectors.toList());
    }

    private TeacherResponseDTO mapToTeacherResponseDTO(Teacher teacher) {
        List<String> sectionNames = teacher.getSections() != null ?
                teacher.getSections().stream().map(Section::getName).collect(Collectors.toList()) :
                Collections.emptyList();

        return new TeacherResponseDTO(
                teacher.getId(),
                teacher.getName(),
                teacher.getEmail(),
                sectionNames
        );
    }

    public void deleteTeacher(String teacherId) {
        if (!teacherRepository.existsById(teacherId)) {
            throw new RuntimeException("Teacher not found with id: " + teacherId);
        }
        teacherRepository.deleteById(teacherId);
    }
}