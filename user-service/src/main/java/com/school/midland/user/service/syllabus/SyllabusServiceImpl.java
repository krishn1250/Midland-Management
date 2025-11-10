package com.school.midland.user.service.syllabus;

import com.school.midland.user.dto.SyllabusDto;
import com.school.midland.user.mappers.SyllabusMapper;
import com.school.midland.user.models.Syllabus;
import com.school.midland.user.repository.SyllabusRepository;
import com.school.midland.user.validators.syllabus.SyllabusValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SyllabusServiceImpl implements SyllabusService {

    private final SyllabusRepository syllabusRepository;
    private final SyllabusValidator syllabusValidator;
    @Override
    public SyllabusDto addSyllabus(Syllabus syllabus) {
        syllabusValidator.validateBeforeSave(syllabus);

        Syllabus saved = syllabusRepository.save(syllabus);
        return SyllabusMapper.mapToDTO(saved);
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
