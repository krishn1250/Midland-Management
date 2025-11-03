package com.school.midland.user.service.teacher;

import com.school.midland.user.dto.PageResponse;
import com.school.midland.user.dto.teacher.TeacherDto;

import java.util.List;
import java.util.UUID;

public interface TeacherService {
    boolean createTeacher(TeacherDto teacherDto);
    TeacherDto getTeacherById(Long id);
    TeacherDto getTeacherByUid(UUID uid);
    TeacherDto getTeacherByCode(String teacherCode);
    public PageResponse<TeacherDto> getAllTeachers(int page, int size, String sortBy);
    TeacherDto updateTeacher(String email, TeacherDto teacherDto);
    Boolean deleteTeacher(String schoolEmail);
    List<TeacherDto> findByDepartment(String department);
    List<TeacherDto>  findByDesignation(String department);
    TeacherDto getByUsername(String username);

}
