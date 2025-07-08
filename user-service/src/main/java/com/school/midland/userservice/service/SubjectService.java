package com.school.midland.userservice.service;

import com.school.midland.userservice.model.Subject;
import com.school.midland.userservice.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public void createSubject(String name, String code) {
    }
    public Subject getSubjectById(String subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + subjectId));

        return subject;
    }
}
