package com.school.midland.userservice.service;

import com.school.midland.userservice.dto.SubjectRequestDTO;
import com.school.midland.userservice.dto.SubjectResponseDTO;
import com.school.midland.userservice.model.Subject;
import com.school.midland.userservice.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List; // <-- Make sure this import is present
import java.util.UUID;
import java.util.stream.Collectors; // <-- And this one

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectResponseDTO createSubject(SubjectRequestDTO request) {
        Subject subject = new Subject();
        subject.setId(UUID.randomUUID().toString());
        subject.setName(request.name());
        
        Subject savedSubject = subjectRepository.save(subject);
        
        return new SubjectResponseDTO(savedSubject.getId(), savedSubject.getName());
    }

    public Subject getSubjectById(String subjectId) {
        return subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + subjectId));
    }

    // --- ADD THIS METHOD ---
    public List<SubjectResponseDTO> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(subject -> new SubjectResponseDTO(subject.getId(), subject.getName()))
                .collect(Collectors.toList());
    }
}