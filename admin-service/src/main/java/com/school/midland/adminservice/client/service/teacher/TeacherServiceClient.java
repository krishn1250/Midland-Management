package com.school.midland.adminservice.client.service.teacher;


import com.school.midland.adminservice.client.dtos.UserCreationResponse;
import com.school.midland.adminservice.client.service.teacher.dto.TeacherResponseDto;
import com.school.midland.commonlib.dtos.StudentDto;
import com.school.midland.commonlib.dtos.TeacherDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TeacherServiceClient {
    Boolean createUserRest(TeacherDto userCreationRequest);
    TeacherResponseDto updateTeacherRest(String teacherCode, TeacherDto teacherDto);
    List<TeacherDto> getAllTeachersRest();
    TeacherDto getTeacherByCodeRest(String teacherCode);
    List<TeacherDto> getTeacherByDepartment(String department);
    TeacherDto getTeacherBySubjectCode(String subjectCode);
    boolean deleteTeacher(String username);
    TeacherDto findByUsername(String username);
}
