package com.school.midland.userservice.service.syllabus;

import com.school.midland.commonlib.dtos.SyllabusDto;
import com.school.midland.userservice.mappers.SyllabusMapper;
import com.school.midland.userservice.models.Syllabus;
import com.school.midland.userservice.repository.SyllabusRepository;
import com.school.midland.userservice.security.JwtTokenValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SyllabusServiceImpl implements SyllabusService {

    private final SyllabusRepository syllabusRepository;
    private final   JwtTokenValidator jwtTokenValidator;

    @Override
    public SyllabusDto addSyllabus(Syllabus syllabus) {
        Syllabus savedSyllabus = syllabusRepository.save(syllabus);
        return SyllabusMapper.mapToDTO(savedSyllabus);
    }

    @Override
    public List<SyllabusDto> getSyllabusByGradeLevelAndSubjectCode(String gradeLevel, String subjectCode) {
        return syllabusRepository.findByGradeLevelAndSubjectCode(gradeLevel, subjectCode)
                .stream()
                .map(SyllabusMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SyllabusDto> getSyllabusByGradeLevelAndSubjectName(String gradeLevel, String subjectName) {
        return syllabusRepository.getSyllabusByGradeLevelAndSubjectName(gradeLevel, subjectName)
                .stream()
                .map(SyllabusMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SyllabusDto getSyllabusById(Long id) {
        Syllabus syllabus = syllabusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Syllabus not found with ID: " + id));
        return SyllabusMapper.mapToDTO(syllabus);
    }

    @Override
    public List<SyllabusDto> getSyllabusByGradeLevel(String gradeLevel) {
        return syllabusRepository.getSyllabusByGradeLevel(gradeLevel)
                .stream()
                .map(SyllabusMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSyllabus(Long id) {
        if (!syllabusRepository.existsById(id)) {
            throw new RuntimeException("Syllabus not found with ID: " + id);
        }
        syllabusRepository.deleteById(id);
    }
}