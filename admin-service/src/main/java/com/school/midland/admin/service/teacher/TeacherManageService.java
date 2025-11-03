package com.school.midland.admin.service.teacher;



import com.school.midland.admin.client.auth.dto.RegisterResponse;
import com.school.midland.admin.client.teacher.dto.TeacherDto;
import com.school.midland.admin.client.teacher.dto.TeacherResponseDto;

import java.util.List;
import java.util.UUID;

public interface TeacherManageService {
    RegisterResponse createTeacher(TeacherDto teacherDto, String token);
    boolean deleteTeacher(String email, String token);
    TeacherResponseDto updateTeacher(String email, TeacherDto updatedDto, String token);
    TeacherResponseDto getById(Long id, String token);
    TeacherResponseDto getByUid(UUID uid, String token);
    TeacherResponseDto getByCode(String code, String token);
    List<TeacherResponseDto> getAllTeachers(int page, int size, String sortBy, String token);
    List<TeacherResponseDto> getByDepartment(String department, String token);
    List<TeacherResponseDto> getByDesignation(String designation, String token);
    TeacherResponseDto getByUsername(String username, String token);
}
