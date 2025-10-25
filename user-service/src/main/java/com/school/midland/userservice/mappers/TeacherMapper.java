package com.school.midland.userservice.mappers;

import com.school.midland.commonlib.dtos.TeacherDto;
import com.school.midland.userservice.dto.teacher.TeacherResponseDto;
import com.school.midland.userservice.models.Teacher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TeacherMapper {
    public  TeacherDto toDto(Teacher teacher) {
        if (teacher == null) return null;

        return TeacherDto.builder()
                .username(teacher.getUsername())
                .teacherUid(teacher.getTeacherUid())
                .teacherCode(teacher.getTeacherCode())
                .fullName(teacher.getFullName())
                .personalEmail(teacher.getPersonalEmail())
                .schoolEmail(teacher.getSchoolEmail())
                .phoneNumber(teacher.getPhoneNumber())
                .qualification(teacher.getQualification())
                .department(teacher.getDepartment())
                .joinDate(teacher.getJoinDate())
                .designation(teacher.getDesignation())
                .profileImage(teacher.getProfileImage())
                .schoolCode(teacher.getSchoolCode())
                .build();
    }

    public  Teacher toEntity(TeacherDto dto) {
        if (dto == null) return null;
        String parts[]={};
        if(dto.getFullName()!=null) {
            parts = dto.getFullName().trim().split("\\s+", 2);
        }
        return Teacher.builder()
                .teacherUid(dto.getTeacherUid() != null ? dto.getTeacherUid() : UUID.randomUUID())
                .username(dto.getUsername())
                .teacherCode(dto.getTeacherCode())
                .fullName(dto.getFullName())
                .firstName(parts.length>0?parts[0]:"")
                .lastName(parts.length>1?parts[1]:"")
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
                .schoolCode(dto.getSchoolCode())
                .build();
    }

    public  TeacherResponseDto toResponseDto(Teacher teacher) {
        if (teacher == null) {
            return null;
        }

        return TeacherResponseDto.builder()
                .username(teacher.getUsername())
                .teacherCode(teacher.getTeacherCode())
                .fullName(teacher.getFullName())
                .personalEmail(teacher.getPersonalEmail())
                .phoneNumber(teacher.getPhoneNumber())
                .qualification(teacher.getQualification())
                .department(teacher.getDepartment())
                .joinDate(teacher.getJoinDate())
                .designation(teacher.getDesignation())
                .profileImage(teacher.getProfileImage())
                .schoolEmail(teacher.getSchoolEmail())
                .schoolCode(teacher.getSchoolCode())
                .build();
    }

}
