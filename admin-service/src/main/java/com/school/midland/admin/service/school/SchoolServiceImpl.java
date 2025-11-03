package com.school.midland.admin.service.school;

import com.school.midland.admin.dtos.school.SchoolDto;
import com.school.midland.admin.exception.AdminException;
import com.school.midland.admin.mapper.SchoolMapper;
import com.school.midland.admin.models.School;
import com.school.midland.admin.repo.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    @Override
    public SchoolDto createSchool(SchoolDto dto) {
        if (dto == null)
            throw new AdminException("School data cannot be null", HttpStatus.BAD_REQUEST);

        if (schoolRepository.findBySchoolCode(dto.getSchoolCode()).isPresent())
            throw new AdminException("School code already exists", HttpStatus.CONFLICT);

//        if (schoolRepository.existsByEmail(dto.getEmail()))
//            throw new AdminException("Email already exists", HttpStatus.CONFLICT);
//
//        if (schoolRepository.existsBySchoolName(dto.getSchoolName()))
//            throw new AdminException("School name already exists", HttpStatus.CONFLICT);

        School entity = schoolMapper.toEntity(dto);
        entity.setIsActive(true);
        entity.setSchoolUid(UUID.randomUUID());

        School saved = schoolRepository.save(entity);
        return schoolMapper.toDto(saved);
    }

    @Override
    public List<SchoolDto> getAllSchools() {
        List<School> entities = schoolRepository.findAll();
        return entities.stream()
                .map(schoolMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SchoolDto getSchoolBySchoolCode(String schoolCode) {
        School school = schoolRepository.findBySchoolCode(schoolCode)
                .orElseThrow(() -> new AdminException("School not found with code: " + schoolCode, HttpStatus.NOT_FOUND));

        return schoolMapper.toDto(school);
    }

    @Override
    public List<SchoolDto> getSchoolByCountry(String country) {
        List<School> schools = schoolRepository.findByCountry(country)
                .orElseThrow(() -> new AdminException("No schools found in country: " + country, HttpStatus.NOT_FOUND));

        return schools.stream().map(schoolMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<SchoolDto> getSchoolByState(String state) {
        List<School> schools = schoolRepository.findByState(state)
                .orElseThrow(() -> new AdminException("No schools found in state: " + state, HttpStatus.NOT_FOUND));

        return schools.stream().map(schoolMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<SchoolDto> getSchoolByCity(String city) {
        List<School> schools = schoolRepository.findByCity(city)
                .orElseThrow(() -> new AdminException("No schools found in city: " + city, HttpStatus.NOT_FOUND));

        return schools.stream().map(schoolMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<SchoolDto> getSchoolByLocation(String country, String state, String city) {
        List<School> schools = schoolRepository.findByCountryAndStateAndCity(country, state, city)
                .orElseThrow(() -> new AdminException("No schools found at specified location", HttpStatus.NOT_FOUND));

        return schools.stream().map(schoolMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public SchoolDto updateSchool(String schoolCode, SchoolDto dto) {
        School existing = schoolRepository.findBySchoolCode(schoolCode)
                .orElseThrow(() -> new AdminException("School not found with code: " + schoolCode, HttpStatus.NOT_FOUND));

        if (dto.getEmail() != null && !dto.getEmail().equals(existing.getEmail()) &&
                schoolRepository.existsByEmail(dto.getEmail())) {
            throw new AdminException("Email already exists", HttpStatus.CONFLICT);
        }

        if (dto.getSchoolName() != null && !dto.getSchoolName().equals(existing.getSchoolName()) &&
                schoolRepository.existsBySchoolName(dto.getSchoolName())) {
            throw new AdminException("School name already exists", HttpStatus.CONFLICT);
        }

        existing.setSchoolName(dto.getSchoolName());
        existing.setEmail(dto.getEmail());
        existing.setCountry(dto.getCountry());
        existing.setState(dto.getState());
        existing.setCity(dto.getCity());
        existing.setAddress(dto.getAddress());
        existing.setPhoneNumber(dto.getPhoneNumber());
        existing.setWebsite(dto.getWebsite());

        School updated = schoolRepository.save(existing);
        return schoolMapper.toDto(updated);
    }

    @Override
    public void deactivateSchool(UUID schoolUid) {
        School school = schoolRepository.findAll()
                .stream()
                .filter(s -> s.getSchoolUid().equals(schoolUid))
                .findFirst()
                .orElseThrow(() -> new AdminException("School not found with UID: " + schoolUid, HttpStatus.NOT_FOUND));

        school.setIsActive(false);
        schoolRepository.save(school);
    }
}
