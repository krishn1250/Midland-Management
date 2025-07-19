package com.school.midland.userservice.service.subject;

import com.school.midland.commonlib.dtos.SubjectDto;
import com.school.midland.userservice.models.Subject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubjectService {
    Subject createSubject(SubjectDto dto);
    SubjectDto updateSubject(String code, SubjectDto dto);
    boolean deleteSubject(String code);
    List<SubjectDto> getAllSubjects();
    List<SubjectDto> getSubjectsByGrade(String gradeLevel);
    List<SubjectDto> getSubjectsByTeacher(String teacherCode);
    SubjectDto getSubjectByCode(String code);
}
