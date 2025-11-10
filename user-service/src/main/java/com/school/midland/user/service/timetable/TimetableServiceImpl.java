package com.school.midland.user.service.timetable;


import com.school.midland.user.dto.TimetableDto;
import com.school.midland.user.exception.UserException;
import com.school.midland.user.mappers.TimetableMapper;
import com.school.midland.user.models.Timetable;
import com.school.midland.user.utils.validators.TimetableValidator;
import com.school.midland.user.repository.TimetableRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService {

    private final TimetableRepository timetableRepository;
    private final TimetableMapper timetableMapper;
    private final TimetableValidator timetableValidator;
    private static final Logger log = LoggerFactory.getLogger(TimetableServiceImpl.class);

    @Override
    public TimetableDto createTimetable(TimetableDto timetableDto) {
        timetableValidator.validateCreateRequest(timetableDto);
        Timetable entity = timetableMapper.toEntity(timetableDto);
        entity.setIsActive(true);
        Timetable saved = timetableRepository.save(entity);
        log.info("Created timetable [{}-{}-{}-{}]",
                saved.getGradeLevel(), saved.getSection(),
                saved.getDayOfWeek(), saved.getPeriodNumber());
        return timetableMapper.toDto(saved);
    }

    @Override
    public TimetableDto updateTimetable(String timetableCode, TimetableDto timetableDto) {
        timetableValidator.validateUpdateRequest(timetableDto);
        Timetable existing = timetableRepository.findByTimetableCode(timetableCode)
                .orElseThrow(() -> new UserException("Timetable not found", HttpStatus.NOT_FOUND));

        existing.setStartTime(timetableDto.getStartTime());
        existing.setEndTime(timetableDto.getEndTime());
        existing.setSubjectCode(timetableDto.getSubjectCode());
        existing.setSubjectName(timetableDto.getSubjectName());
        existing.setTeacherCode(timetableDto.getTeacherCode());
        existing.setRoomNumber(timetableDto.getRoomNumber());
        existing.setAcademicYear(timetableDto.getAcademicYear());
        existing.setUpdatedAt(java.time.LocalDateTime.now());

        Timetable updated = timetableRepository.save(existing);
        log.info("Updated timetable [{}]", timetableCode);
        return timetableMapper.toDto(updated);
    }

    @Override
    public List<TimetableDto> createTimetables(List<TimetableDto> timetableDtos) {
        List<Timetable> toSave = timetableDtos.stream()
                .filter(dto -> !timetableRepository.existsByGradeLevelAndSectionAndDayOfWeekAndPeriodNumberAndIsActiveTrue(
                        dto.getGradeLevel(), dto.getSection(), dto.getDayOfWeek(), dto.getPeriodNumber()))
                .map(timetableMapper::toEntity)
                .collect(Collectors.toList());

        List<Timetable> saved = timetableRepository.saveAll(toSave);
        log.info("Created {} timetables", saved.size());

        return saved.stream().map(timetableMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<TimetableDto> getTeacherTimetable(String teacherCode) {
        return timetableRepository.findByTeacherCodeAndIsActiveTrue(teacherCode)
                .stream().map(timetableMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<TimetableDto> getTimetableForClass(String gradeLevel, String section, String dayOfWeek) {
        return timetableRepository.findByGradeLevelAndSectionAndDayOfWeekAndIsActiveTrue(
                gradeLevel, section, dayOfWeek
        ).stream().map(timetableMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<TimetableDto> getAllActiveTimetables() {
        return timetableRepository.findByIsActiveTrue()
                .stream().map(timetableMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public String deactivateTimetable(String timetableCode) {
        Timetable existing = timetableRepository.findByTimetableCode(timetableCode)
                .orElseThrow(() -> new UserException("Timetable not found", HttpStatus.NOT_FOUND));

        existing.setIsActive(false);
        timetableRepository.save(existing);

        log.info("Deactivated timetable [{}]", timetableCode);
        return "Timetable deactivated successfully.";
    }
}
