package com.school.midland.userservice.service.teacher;

import com.school.midland.commonlib.dtos.TeacherDto;
import com.school.midland.userservice.dto.teacher.TeacherResponseDto;
import com.school.midland.userservice.models.Teacher;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeacherService {
    boolean createTeacher(TeacherDto teacherDto);
    TeacherDto getTeacherById(Long id);
    TeacherDto getTeacherByEmail(String email);
    TeacherDto getTeacherByCode(String teacherCode);
    List<TeacherDto> getAllTeachers();
    TeacherResponseDto updateTeacher(String teacherCode, TeacherDto teacherDto);
    Boolean deleteTeacher(String email);
    List<TeacherDto> findByDepartment(String department);
    List<TeacherDto>  findByDesignation(String department);
    TeacherDto getByUsername(String username);

}
