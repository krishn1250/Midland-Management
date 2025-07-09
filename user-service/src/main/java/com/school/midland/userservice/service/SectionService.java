// --- File: service/SectionService.java ---
package com.school.midland.userservice.service;

import com.school.midland.userservice.dto.SectionRequestDTO;
import com.school.midland.userservice.dto.SectionResponseDTO;
import com.school.midland.userservice.model.Section;
import com.school.midland.userservice.model.Subject;
import com.school.midland.userservice.model.Teacher;
import com.school.midland.userservice.repository.SectionRepository;
import com.school.midland.userservice.repository.SubjectRepository;
import com.school.midland.userservice.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectionService {
    private final SectionRepository sectionRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;

    // ... (getAllSections, getSectionById, createSection methods are unchanged) ...
    public List<SectionResponseDTO> getAllSections() {
        return sectionRepository.findAll().stream()
                .map(SectionResponseDTO::new)
                .toList();
    }

    public Section getSectionById(String sectionId) {
        return sectionRepository.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("Section not found with id: " + sectionId));
    }

    @Transactional
    public SectionResponseDTO createSection(SectionRequestDTO sectionRequestDTO) {
        // ... (This method is unchanged)
        Section section = new Section();
        section.setId(UUID.randomUUID().toString());
        section.setName(sectionRequestDTO.name());

        if (sectionRequestDTO.subjectIds() != null && !sectionRequestDTO.subjectIds().isEmpty()) {
            List<Subject> subjects = subjectRepository.findAllById(sectionRequestDTO.subjectIds());
            section.setSubjects(subjects);
        } else {
            section.setSubjects(Collections.emptyList());
        }

        if (sectionRequestDTO.teacherIds() != null && !sectionRequestDTO.teacherIds().isEmpty()) {
            List<Teacher> teachers = teacherRepository.findAllById(sectionRequestDTO.teacherIds());
            section.setTeachers(teachers);
            for (Teacher teacher : teachers) {
                teacher.getSections().add(section);
            }
        } else {
             section.setTeachers(Collections.emptyList());
        }

        Section savedSection = sectionRepository.save(section);
        return new SectionResponseDTO(savedSection);
    }


    // --- ADD THIS NEW METHOD FOR UPDATING ---
    @Transactional
    public SectionResponseDTO updateSection(String sectionId, SectionRequestDTO request) {
        // 1. Find the existing section
        Section section = getSectionById(sectionId);
        section.setName(request.name());

        // 2. Update subjects (Section owns this relationship, so it's straightforward)
        if (request.subjectIds() != null) {
            List<Subject> newSubjects = subjectRepository.findAllById(request.subjectIds());
            section.setSubjects(newSubjects);
        }

        // 3. Update teachers (Teacher owns this relationship, so it requires careful handling)
        if (request.teacherIds() != null) {
            handleTeacherAssociation(section, request.teacherIds());
        }

        Section updatedSection = sectionRepository.save(section);
        return new SectionResponseDTO(updatedSection);
    }
    
    private void handleTeacherAssociation(Section section, List<String> newTeacherIdsList) {
        Set<String> newTeacherIds = Set.copyOf(newTeacherIdsList);
        List<Teacher> currentTeachers = section.getTeachers();
        Set<String> currentTeacherIds = currentTeachers.stream().map(Teacher::getId).collect(Collectors.toSet());

        // Teachers to remove: are in current but not in new
        List<Teacher> teachersToRemove = currentTeachers.stream()
                .filter(teacher -> !newTeacherIds.contains(teacher.getId()))
                .toList();

        for (Teacher teacher : teachersToRemove) {
            teacher.getSections().remove(section);
        }
        
        // Teachers to add: are in new but not in current
        Set<String> teacherIdsToAdd = newTeacherIds.stream()
                .filter(id -> !currentTeacherIds.contains(id))
                .collect(Collectors.toSet());

        if (!teacherIdsToAdd.isEmpty()) {
            List<Teacher> teachersToAdd = teacherRepository.findAllById(teacherIdsToAdd);
            for (Teacher teacher : teachersToAdd) {
                teacher.getSections().add(section);
            }
        }
        
        // Finally, set the updated list on the section object for the response DTO
        section.setTeachers(teacherRepository.findAllById(newTeacherIdsList));
    }
}