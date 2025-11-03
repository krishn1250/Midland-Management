package com.school.midland.user.mappers;

import com.school.midland.user.dto.teacher.TeacherDto;
import com.school.midland.user.models.Teacher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;


@Component
public class TeacherMapper {
    public TeacherDto toDto(Teacher teacher) {
        if (teacher == null) return null;

        return TeacherDto.builder()
                .username(teacher.getUsername())
                .teacherUid(teacher.getTeacherUid())
                .teacherCode(teacher.getTeacherCode())
                .fullName(teacher.getFullName())
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
                .firstName(parts.length>0?parts[0]:"")
                .lastName(parts.length>1?parts[1]:"")
                .fullName(dto.getFullName())
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
}
