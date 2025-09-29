package com.school.midland.userservice.service.exam;

import com.school.midland.commonlib.dtos.ExamDto;

import java.util.List;
import java.util.UUID;

public interface ExamService {
    ExamDto createExam(ExamDto examDto);
    List<ExamDto> getAllExams();
    ExamDto getExamById(Integer examId);
    ExamDto updateExam(Integer examId, ExamDto examDto);
    void deleteExam(Integer examId);
}