package com.school.midland.adminservice.service.subject;

import com.school.midland.adminservice.client.service.subject.SubjectServiceClient;
import com.school.midland.commonlib.dtos.SubjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectManageServiceImpl implements SubjectManageService {

    private final SubjectServiceClient subjectServiceClient;

    @Override
    public SubjectDto createSubject(SubjectDto dto) {
        return subjectServiceClient.createSubject(dto);
    }

    @Override
    public List<SubjectDto> createSubjects(List<SubjectDto> subjectDtos) {
        return subjectServiceClient.createSubjects(subjectDtos);
    }

    @Override
    public SubjectDto updateSubject(String subjectCode, SubjectDto dto) {
        return subjectServiceClient.updateSubject(subjectCode, dto);
    }

    @Override
    public boolean deleteSubject(String code) {
        return subjectServiceClient.deleteSubject(code);
    }

    @Override
    public List<SubjectDto> getAllSubjects() {
        return subjectServiceClient.getAllSubjects();
    }

    @Override
    public List<SubjectDto> getSubjectsByGrade(String gradeLevel) {
        return subjectServiceClient.getSubjectsByGrade(gradeLevel);
    }

    @Override
    public List<SubjectDto> getSubjectsByTeacher(String teacherCode) {
        return subjectServiceClient.getSubjectsByTeacher(teacherCode);
    }

    @Override
    public SubjectDto getSubjectByCode(String code) {
        return subjectServiceClient.getSubjectByCode(code);
    }
}
