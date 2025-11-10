package com.school.midland.user.service.subject;

import com.school.midland.user.dto.PageResponse;
import com.school.midland.user.dto.SubjectDto;
import com.school.midland.user.exception.UserException;
import com.school.midland.user.mappers.SubjectMapper;
import com.school.midland.user.models.Subject;
import com.school.midland.user.repository.SubjectRepository;
import com.school.midland.user.util.ValidatorUtils;
import com.school.midland.user.validators.subject.SubjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectValidator subjectValidator;

    @Override
    @Transactional
    public boolean createSubject(SubjectDto dto) {
        subjectValidator.validateForCreate(dto);
        subjectRepository.save(SubjectMapper.toEntity(dto));
        return true;
    }

    @Override
    @Transactional
    public List<SubjectDto> createSubjects(List<SubjectDto> dtos) {
        if (dtos == null || dtos.isEmpty())
            throw new UserException("Subject list cannot be empty", HttpStatus.BAD_REQUEST);

        Map<String, SubjectDto> uniqueSubjects = dtos.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(
                        SubjectDto::getSubjectCode,
                        dto -> dto,
                        (existing, duplicate) -> existing
                ));

        Set<String> existingCodes = subjectRepository.findAll().stream()
                .map(Subject::getSubjectCode)
                .collect(Collectors.toSet());

        List<Subject> newSubjects = uniqueSubjects.values().stream()
                .filter(dto -> !existingCodes.contains(dto.getSubjectCode()))
                .map(SubjectMapper::toEntity)
                .collect(Collectors.toList());

        if (newSubjects.isEmpty())
            throw new UserException("All subject codes already exist", HttpStatus.CONFLICT);

        subjectRepository.saveAll(newSubjects);
        return SubjectMapper.toDtoList(newSubjects);
    }

    @Override
    public SubjectDto getSubjectByCode(String code) {
        ValidatorUtils.validateField(code, "Subject code");
        Subject subject = subjectRepository.findBySubjectCode(code)
                .orElseThrow(() -> new UserException("Subject not found: " + code, HttpStatus.NOT_FOUND));
        return SubjectMapper.toDto(subject);
    }

    @Override
    @Transactional
    public SubjectDto updateSubject(String subjectCode, SubjectDto dto) {
        ValidatorUtils.validateField(subjectCode, "Subject code");

        Subject existing = subjectRepository.findBySubjectCode(subjectCode)
                .orElseThrow(() -> new UserException("Subject not found: " + subjectCode, HttpStatus.NOT_FOUND));

        if (dto.getSubjectName() != null) existing.setSubjectName(dto.getSubjectName());
        if (dto.getCurriculumType() != null) existing.setCurriculumType(dto.getCurriculumType());
        if (dto.getGradeLevel() != null) existing.setGradeLevel(dto.getGradeLevel());
        if (dto.getTeacherCode() != null) existing.setTeacherCode(dto.getTeacherCode());
        if (dto.getSchoolCode() != null) existing.setSchoolCode(dto.getSchoolCode());
        existing.setUpdatedBy("system");

        subjectRepository.save(existing);
        return SubjectMapper.toDto(existing);
    }

    @Override
    @Transactional
    public boolean deleteSubject(String subjectCode) {
        ValidatorUtils.validateField(subjectCode, "Subject code");

        Subject subject = subjectRepository.findBySubjectCode(subjectCode)
                .orElseThrow(() -> new UserException("Subject not found: " + subjectCode, HttpStatus.NOT_FOUND));

        subjectRepository.delete(subject);
        return true;
    }

    @Override
    public PageResponse<SubjectDto> getAllSubjects(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Subject> subjectPage = subjectRepository.findAll(pageable);
        return buildPageResponse(subjectPage);
    }

    @Override
    public PageResponse<SubjectDto> searchSubjects(String gradeLevel, String teacherCode, String schoolCode,
                                                   String curriculumType, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Subject> subjectPage = subjectRepository.searchSubjects(gradeLevel, teacherCode, curriculumType, schoolCode, pageable);
        return buildPageResponse(subjectPage);
    }



    @Override
    public List<SubjectDto> getSubjectsByGradeAndTeacher(String gradeLevel, String teacherCode) {
        ValidatorUtils.validateField(gradeLevel, "Grade level");
        ValidatorUtils.validateField(teacherCode, "Teacher code");
        return SubjectMapper.toDtoList(subjectRepository.findByGradeLevelAndTeacherCode(gradeLevel, teacherCode));
    }

    private PageResponse<SubjectDto> buildPageResponse(Page<Subject> subjectPage) {
        List<SubjectDto> dtos = SubjectMapper.toDtoList(subjectPage.getContent());
        return new PageResponse<>(dtos, subjectPage.getNumber(), subjectPage.getSize(),
                subjectPage.getTotalElements(), subjectPage.getTotalPages(), subjectPage.isLast());
    }
}
