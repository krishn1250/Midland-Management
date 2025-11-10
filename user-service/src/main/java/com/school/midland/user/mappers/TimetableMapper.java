package com.school.midland.user.mappers;


import com.school.midland.user.dto.TimetableDto;
import com.school.midland.user.models.Timetable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimetableMapper {

    public Timetable toEntity(TimetableDto dto) {
        if (dto == null) return null;

        Timetable entity = new Timetable();
        entity.setGradeLevel(dto.getGradeLevel());
        entity.setSection(dto.getSection());
        entity.setDayOfWeek(dto.getDayOfWeek());
        entity.setPeriodNumber(dto.getPeriodNumber());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        entity.setSubjectCode(dto.getSubjectCode());
        entity.setSubjectName(dto.getSubjectName());
        entity.setTeacherCode(dto.getTeacherCode());
        entity.setRoomNumber(dto.getRoomNumber());
        entity.setAcademicYear(dto.getAcademicYear());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(dto.getUpdatedAt());
        return entity;
    }

    public TimetableDto toDto(Timetable entity) {
        if (entity == null) return null;

        TimetableDto dto = new TimetableDto();
        dto.setId(entity.getId());
        dto.setGradeLevel(entity.getGradeLevel());
        dto.setSection(entity.getSection());
        dto.setDayOfWeek(entity.getDayOfWeek());
        dto.setPeriodNumber(entity.getPeriodNumber());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        dto.setSubjectCode(entity.getSubjectCode());
        dto.setSubjectName(entity.getSubjectName());
        dto.setTeacherCode(entity.getTeacherCode());
        dto.setRoomNumber(entity.getRoomNumber());
        dto.setAcademicYear(entity.getAcademicYear());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(LocalDateTime.now());
        return dto;
    }
}