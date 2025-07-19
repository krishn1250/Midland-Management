package com.school.midland.userservice.mappers;

import com.school.midland.commonlib.dtos.TeacherDto;
import com.school.midland.userservice.models.Teacher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;


public class TeacherMapper {
    public static TeacherDto toDto(Teacher teacher) {
        if (teacher == null) return null;

        return TeacherDto.builder()
                .username(teacher.getUsername())
                .teacherUid(teacher.getTeacherUid())
                .teacherCode(teacher.getTeacherCode())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .personalEmail(teacher.getPersonalEmail())
                .schoolEmail(teacher.getSchoolEmail())
                .phoneNumber(teacher.getPhoneNumber())
                .qualification(teacher.getQualification())
                .department(teacher.getDepartment())
                .joinDate(teacher.getJoinDate())
                .designation(teacher.getDesignation())
                .profileImage(teacher.getProfileImage())
                .build();
    }

    public static Teacher toEntity(TeacherDto dto) {
        if (dto == null) return null;

        return Teacher.builder()
                .teacherUid(dto.getTeacherUid() != null ? dto.getTeacherUid() : UUID.randomUUID())
                .username(dto.getUsername())
                .teacherCode(dto.getTeacherCode())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .personalEmail(dto.getPersonalEmail())
                .schoolEmail(dto.getSchoolEmail())
                .phoneNumber(dto.getPhoneNumber())
                .qualification(dto.getQualification())
                .department(dto.getDepartment())
                .joinDate(dto.getJoinDate())
                .designation(dto.getDesignation())
                .profileImage(dto.getProfileImage())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
