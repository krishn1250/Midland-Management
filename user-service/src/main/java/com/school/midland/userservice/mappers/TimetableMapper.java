package com.school.midland.userservice.mappers;

import com.school.midland.commonlib.dtos.TimetableDto;
import com.school.midland.userservice.models.Timetable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TimetableMapper {
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    Timetable toEntity(TimetableDto dto);
    TimetableDto toDto(Timetable entity);
}