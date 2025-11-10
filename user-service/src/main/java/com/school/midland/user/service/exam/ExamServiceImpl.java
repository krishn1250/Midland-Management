package com.school.midland.user.service.exam;

import com.school.midland.user.dto.ExamDto;
import com.school.midland.user.mappers.ExamMapper;
import com.school.midland.user.models.Exams;
import com.school.midland.user.repository.ExamRepository;
import com.school.midland.user.validators.exam.ExamValidator;
import com.school.midland.user.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final ExamValidator examValidator;

    @Override
    @Transactional
    public ExamDto createExam(ExamDto examDto) {
        Exams exam = ExamMapper.toEntity(examDto);
        examValidator.validateForCreate(exam);
        Exams saved = examRepository.save(exam);
        return ExamMapper.toDto(saved);
    }

    @Override
    public Page<ExamDto> getAllExams(int page, int size, String sortBy, String sortDir) {
        Sort sort = Sort.by(sortBy == null ? "createdAt" : sortBy);
        sort = "desc".equalsIgnoreCase(sortDir) ? sort.descending() : sort.ascending();
        Pageable pageable = PageRequest.of(Math.max(0, page), Math.max(1, size), sort);

        Page<Exams> exams = examRepository.findAll(pageable);
        return exams.map(ExamMapper::toDto);
    }

    @Override
    public ExamDto getExamById(Long examId) {
        Exams exam = examRepository.findById(examId)
                .orElseThrow(() -> new UserException("Exam not found with id: " + examId, HttpStatus.NOT_FOUND));
        return ExamMapper.toDto(exam);
    }

    @Override
    @Transactional
    public ExamDto updateExam(Long examId, ExamDto examDto) {
        Exams existing = examRepository.findById(examId)
                .orElseThrow(() -> new UserException("Exam not found with id: " + examId, HttpStatus.NOT_FOUND));

        if (examDto.getName() != null) existing.setName(examDto.getName());
        if (examDto.getAcademicYear() != null) existing.setAcademicYear(examDto.getAcademicYear());
        if (examDto.getExamType() != null) existing.setExamType(examDto.getExamType());
        if (examDto.getStartTime() != null) existing.setStartTime(examDto.getStartTime());
        if (examDto.getEndTime() != null) existing.setEndTime(examDto.getEndTime());
        if (examDto.getGradeLevel() != null) existing.setGradeLevel(examDto.getGradeLevel());
        existing.setUpdatedAt(java.time.LocalDateTime.now());

        examValidator.validateForUpdate(existing, existing);

        Exams saved = examRepository.save(existing);
        return ExamMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteExam(Long examId) {
        Exams exam = examRepository.findById(examId)
                .orElseThrow(() -> new UserException("Exam not found with id: " + examId, HttpStatus.NOT_FOUND));

        // Soft delete
        exam.setIsActive(false);
        exam.setUpdatedAt(java.time.LocalDateTime.now());
        examRepository.save(exam);
    }

    @Override
    public List<ExamDto> getExamsByGrade(String gradeLevel) {
        return examRepository.findByGradeLevel(gradeLevel)
                .stream().filter(Exams::getIsActive)
                .map(ExamMapper::toDto)
                .toList();
    }
}
