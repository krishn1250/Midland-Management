package com.school.midland.userservice.service;

import com.school.midland.userservice.model.Section;
import com.school.midland.userservice.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionService {
    private final SectionRepository sectionRepository;

    public List<Section> getAllSections(String sectionName) {

        List<Section> sections = sectionRepository.findAll();

        return sections;
    }

    public Section getSectionById(String sectionId) {
        return sectionRepository.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("Section not found with id: " + sectionId));
    }

    public Section createSection(Section section) {
        return sectionRepository.save(section);
    }

}
