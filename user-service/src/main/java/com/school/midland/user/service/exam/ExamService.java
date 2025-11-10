package com.school.midland.user.service.exam;

import com.school.midland.user.dto.ExamDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExamService {
    ExamDto createExam(ExamDto examDto);
    Page<ExamDto> getAllExams(int page, int size, String sortBy, String sortDir);
    ExamDto getExamById(Long examId);
    ExamDto updateExam(Long examId, ExamDto examDto);
    void deleteExam(Long examId);
    List<ExamDto> getExamsByGrade(String gradeLevel);
}
