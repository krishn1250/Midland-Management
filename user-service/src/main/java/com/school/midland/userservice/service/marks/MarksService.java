package com.school.midland.userservice.service.marks;

import com.school.midland.commonlib.dtos.MarksDto;

import java.util.List;

public interface MarksService {
    MarksDto addMarks(MarksDto dto);
    MarksDto updateMarks(Long id, MarksDto dto);
    void deleteMarks(Long id);
    MarksDto getMarksById(Long id);
    List<MarksDto> getStudentMarks(String admissionNumber, Long examId);
    List<MarksDto> getExamMarks(Long examId);
    List<MarksDto> getAllMarks();
}