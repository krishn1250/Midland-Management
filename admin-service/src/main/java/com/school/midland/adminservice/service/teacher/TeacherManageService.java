package com.school.midland.adminservice.service.teacher;

import com.school.midland.adminservice.client.dtos.UserCreationResponse;
import com.school.midland.adminservice.client.service.teacher.dto.TeacherResponseDto;
import com.school.midland.commonlib.dtos.TeacherDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface TeacherManageService {
    UserCreationResponse createTeacher(TeacherDto teacherDto, String token);
//    TeacherDto getTeacherById(Long id);
//    TeacherDto getTeacherByUid(UUID uid);
    List<TeacherDto> getTeacherByDepartment(String department);
    TeacherDto getTeacherByUsername(String username);
    TeacherDto getTeacherByCode(String teacherCode);
    List<TeacherDto> getAllTeachers();
    public TeacherResponseDto updateTeacher(String email, TeacherDto updatedDto, String token);
    boolean deleteTeacher(String email,String token);
}
