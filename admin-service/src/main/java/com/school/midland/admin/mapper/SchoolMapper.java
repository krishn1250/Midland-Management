package com.school.midland.admin.mapper;

import com.school.midland.admin.dtos.school.SchoolDto;
import com.school.midland.admin.models.School;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SchoolMapper {

    public School toEntity(SchoolDto dto) {
        if (dto == null)
            return null;

        School entity = new School();
//        entity.setSchoolId(null); // handled by DB
        entity.setSchoolUid(UUID.randomUUID());
        entity.setIsActive(true);

        entity.setSchoolCode(dto.getSchoolCode());
        entity.setSchoolName(dto.getSchoolName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setLocation(dto.getLocation());
        entity.setPinCode(dto.getPinCode());
        entity.setTimezone(dto.getTimezone());
        entity.setWebsite(dto.getWebsite());
        entity.setAddress(dto.getAddress());
        entity.setCountry(dto.getCountry());
        entity.setState(dto.getState());
        entity.setCity(dto.getCity());

        return entity;
    }

    public SchoolDto toDto(School entity) {
        if (entity == null)
            return null;

        SchoolDto dto = new SchoolDto();
        dto.setSchoolId(entity.getSchoolId());
        dto.setSchoolCode(entity.getSchoolCode());
        dto.setSchoolName(entity.getSchoolName());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setWebsite(entity.getWebsite());
        dto.setAddress(entity.getAddress());
        dto.setCountry(entity.getCountry());
        dto.setLocation(entity.getLocation());
        dto.setPinCode(entity.getPinCode());
        dto.setState(entity.getState());
        dto.setCity(entity.getCity());
        dto.setSchoolUid(entity.getSchoolUid());
        dto.setIsActive(entity.getIsActive());

        return dto;
    }
}
