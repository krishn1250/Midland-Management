package com.school.midland.userservice.service.exam;

import com.school.midland.commonlib.dtos.ExamDto;
import com.school.midland.userservice.mappers.ExamMapper;
import com.school.midland.userservice.models.Exams;
import com.school.midland.userservice.repository.ExamRepository;
import com.school.midland.userservice.security.JwtTokenValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;

    @Override
    public ExamDto createExam(ExamDto examDto) {
        Exams exam = ExamMapper.toEntity(examDto);
        Exams savedExam = examRepository.save(exam);
        return ExamMapper.toDto(savedExam);
    }

    @Override
    public List<ExamDto> getAllExams() {
        return examRepository.findAll()
                .stream()
                .map(ExamMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExamDto getExamById(Integer examId) {
        Exams exam = examRepository.findById(Long.valueOf(examId))
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + examId));
        return ExamMapper.toDto(exam);
    }

    @Override
    public ExamDto updateExam(Integer examId, ExamDto examDto) {
        Exams exam = examRepository.findById(Long.valueOf(examId))
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + examId));

        exam.setName(examDto.getName());
        exam.setAcademicYear(examDto.getAcademicYear());
        exam.setExamType(examDto.getExamType());
        exam.setStartTime(examDto.getStartTime());
        exam.setEndTime(examDto.getEndTime());
        exam.setGradeLevel(examDto.getGradeLevel());
        exam.setUpdatedAt(java.time.LocalDateTime.now());

        return ExamMapper.toDto(examRepository.save(exam));
    }

    @Override
    public void deleteExam(Integer examId) {
        if (!examRepository.existsById(Long.valueOf(examId))) {
            throw new RuntimeException("Exam not found with id: " + examId);
        }
        examRepository.deleteById(Long.valueOf(examId));
    }
}