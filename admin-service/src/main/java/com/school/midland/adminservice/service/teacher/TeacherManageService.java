package com.school.midland.adminservice.service.teacher;

import com.school.midland.commonlib.dtos.TeacherDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface TeacherManageService {
    TeacherDto createTeacher(TeacherDto teacherDto);
//    TeacherDto getTeacherById(Long id);
//    TeacherDto getTeacherByUid(UUID uid);
    List<TeacherDto> getTeacherByDepartment(String department);
    TeacherDto getTeacherByUsername(String username);
    TeacherDto getTeacherByCode(String teacherCode);
    List<TeacherDto> getAllTeachers();
    TeacherDto updateTeacher(String teacherCode, TeacherDto teacherDto);
    boolean deleteTeacher(String username);
}
