package com.school.midland.userservice.service.syllabus;

import com.school.midland.commonlib.dtos.SyllabusDto;
import com.school.midland.userservice.models.Syllabus;

import java.util.List;

public interface SyllabusService {
    SyllabusDto addSyllabus(Syllabus syllabusDto);
    List<SyllabusDto> getSyllabusByGradeLevelAndSubjectCode(String gradeLevel, String subjectCode);
    List<SyllabusDto> getSyllabusByGradeLevelAndSubjectName(String gradeLevel,String subjectName);
    SyllabusDto getSyllabusById(Long id);
    List<SyllabusDto> getSyllabusByGradeLevel(String gradeLevel);
    void deleteSyllabus(Long id);

}
