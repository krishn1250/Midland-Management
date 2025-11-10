package com.school.midland.user.service.syllabus;

import com.school.midland.user.dto.SyllabusDto;
import com.school.midland.user.models.Syllabus;

import java.util.List;

public interface SyllabusService {
    SyllabusDto addSyllabus(Syllabus syllabus);
    List<SyllabusDto> getSyllabusByGradeLevelAndSubjectCode(String gradeLevel, String subjectCode);
    List<SyllabusDto> getSyllabusByGradeLevelAndSubjectName(String gradeLevel, String subjectName);
    SyllabusDto getSyllabusById(Long id);
    List<SyllabusDto> getSyllabusByGradeLevel(String gradeLevel);
    void deleteSyllabus(Long id);
}
