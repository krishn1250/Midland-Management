package com.school.midland.adminservice.service.subject;

import com.school.midland.commonlib.dtos.SubjectDto;

import java.util.List;

public interface SubjectManageService {
    SubjectDto createSubject(SubjectDto dto);
    List<SubjectDto> createSubjects(List<SubjectDto> subjectDtos);
    SubjectDto updateSubject(String subjectCode, SubjectDto dto);
    boolean deleteSubject(String code);
    List<SubjectDto> getAllSubjects();
    List<SubjectDto> getSubjectsByGrade(String gradeLevel);
    List<SubjectDto> getSubjectsByTeacher(String teacherCode);
    SubjectDto getSubjectByCode(String code);
}
