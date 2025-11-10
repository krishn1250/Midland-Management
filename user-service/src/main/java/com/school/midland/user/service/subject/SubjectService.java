package com.school.midland.user.service.subject;

import com.school.midland.user.dto.PageResponse;
import com.school.midland.user.dto.SubjectDto;
import java.util.List;

public interface SubjectService {

    boolean createSubject(SubjectDto dto);
    List<SubjectDto> createSubjects(List<SubjectDto> dtos);
    SubjectDto getSubjectByCode(String code);
    SubjectDto updateSubject(String subjectCode, SubjectDto dto);
    boolean deleteSubject(String subjectCode);

    PageResponse<SubjectDto> getAllSubjects(int page, int size, String sortBy);
    PageResponse<SubjectDto> searchSubjects(String gradeLevel, String teacherCode, String schoolCode, String curriculumType,
                                            int page, int size, String sortBy);
    List<SubjectDto> getSubjectsByGradeAndTeacher(String gradeLevel, String teacherCode);
}
