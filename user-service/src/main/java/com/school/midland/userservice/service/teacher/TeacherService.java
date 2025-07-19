package com.school.midland.userservice.service.teacher;

import com.school.midland.commonlib.dtos.TeacherDto;
import com.school.midland.userservice.models.Teacher;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeacherService {
    TeacherDto createTeacher(TeacherDto teacherDto);
    TeacherDto getTeacherById(Long id);
    TeacherDto getTeacherByUid(UUID uid);
    TeacherDto getTeacherByCode(String teacherCode);
    List<TeacherDto> getAllTeachers();
    TeacherDto updateTeacher(String teacherCode, TeacherDto teacherDto);
    Boolean deleteTeacher(Long id);
    List<TeacherDto> findByDepartment(String department);
    List<TeacherDto>  findByDesignation(String department);
}
