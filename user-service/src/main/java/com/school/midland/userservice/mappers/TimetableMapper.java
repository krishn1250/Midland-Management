package com.school.midland.userservice.mappers;

import com.school.midland.commonlib.dtos.TimetableDto;
import com.school.midland.userservice.models.Timetable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimetableMapper {  // ✅ must be interface
    Timetable toEntity(TimetableDto dto);
    TimetableDto toDto(Timetable entity);
}