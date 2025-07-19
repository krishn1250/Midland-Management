package com.school.midland.userservice.service.teacher;

import com.school.midland.commonlib.dtos.TeacherDto;
import com.school.midland.commonlib.exception.UserException;
import com.school.midland.userservice.mappers.TeacherMapper;
import com.school.midland.userservice.models.Teacher;
import com.school.midland.userservice.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements  TeacherService{

    private final TeacherRepository teacherRepository;


    @Override
    public TeacherDto createTeacher(TeacherDto teacherDto) {
        if (teacherDto == null ) {
            throw new UserException("fill the details", HttpStatus.BAD_REQUEST);
        }
        if(teacherDto.getTeacherCode() !=null && teacherRepository.findByTeacherCode(teacherDto.getTeacherCode()).isPresent()) {
            throw new UserException("teacher employee id  number already exists", HttpStatus.BAD_REQUEST);
        }
        if(teacherDto.getSchoolEmail()!=null && teacherRepository.findBySchoolEmail(teacherDto.getSchoolEmail()).isPresent()){
            throw  new UserException("teacher email already exists", HttpStatus.BAD_REQUEST);
        }
        final Teacher entity =TeacherMapper.toEntity(teacherDto);
        final Teacher save = teacherRepository.save(entity);
        final TeacherDto dto=TeacherMapper.toDto(save);
        return dto;
    }

    @Override
    public TeacherDto getTeacherById(Long id) {
        return null;
    }

    @Override
    public TeacherDto getTeacherByUid(UUID uid) {
        return null;
    }

    @Override
    public TeacherDto getTeacherByCode(String teacherCode) {
        return null;
    }

    @Override
    public List<TeacherDto> getAllTeachers() {
        return List.of();
    }

    @Override
    public TeacherDto updateTeacher(String teacherCode, TeacherDto teacherDto) {
        return null;
    }

    @Override
    public Boolean deleteTeacher(Long id) {
        return null;
    }

    @Override
    public List<TeacherDto> findByDepartment(String department) {
        return List.of();
    }

    @Override
    public List<TeacherDto> findByDesignation(String department) {
        return List.of();
    }
}
