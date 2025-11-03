package com.school.midland.admin.service.school;

import com.school.midland.admin.dtos.school.SchoolDto;

import java.util.List;
import java.util.UUID;

public interface SchoolService {

    SchoolDto createSchool(SchoolDto dto);

    List<SchoolDto> getAllSchools();

    SchoolDto getSchoolBySchoolCode(String schoolCode);
    List<SchoolDto> getSchoolByCountry(String Country);
    List<SchoolDto> getSchoolByState(String State);
    List<SchoolDto> getSchoolByCity(String city);
    List<SchoolDto>  getSchoolByLocation(String country,String state,String city);
    SchoolDto updateSchool(String schoolCode, SchoolDto dto);

    void deactivateSchool(UUID schoolUid);
}