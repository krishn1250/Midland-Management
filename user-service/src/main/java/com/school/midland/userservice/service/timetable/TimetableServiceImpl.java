package com.school.midland.userservice.service.timetable;

import com.school.midland.commonlib.dtos.TimetableDto;
import com.school.midland.commonlib.exception.UserException;
import com.school.midland.userservice.mappers.TimetableMapper;
import com.school.midland.userservice.models.Timetable;
import com.school.midland.userservice.repository.TimetableRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService {

    private final TimetableRepository timetableRepository;
    private final TimetableMapper timetableMapper;
    private static final Logger log = LoggerFactory.getLogger(TimetableServiceImpl.class);

    @Override
    public TimetableDto createTimetable(TimetableDto timetableDto) {

        boolean exists = timetableRepository.existsByGradeLevelAndSectionAndDayOfWeekAndPeriodNumber(
                timetableDto.getGradeLevel(),
                timetableDto.getSection(),
                timetableDto.getDayOfWeek(),
                timetableDto.getPeriodNumber()
        );

        if (exists) {
            throw new UserException(
                    "Timetable slot already exists for this class, section, day, and period.",
                    HttpStatus.BAD_REQUEST
            );
        }

        Timetable entity = timetableMapper.toEntity(timetableDto);
        // Timestamps will be automatically handled by @CreationTimestamp and @UpdateTimestamp
        Timetable saved = timetableRepository.save(entity);

        log.info("Created timetable: {}-{}-{}-{}", saved.getGradeLevel(), saved.getSection(),
                saved.getDayOfWeek(), saved.getPeriodNumber());

        return timetableMapper.toDto(saved);
    }

    @Override
    public List<TimetableDto> createTimetables(List<TimetableDto> timetableDtos) {
        log.info("Received {} timetables to create", timetableDtos.size());

        List<Timetable> entitiesToSave = timetableDtos.stream()
                .filter(dto -> !timetableRepository.existsByGradeLevelAndSectionAndDayOfWeekAndPeriodNumber(
                        dto.getGradeLevel(),
                        dto.getSection(),
                        dto.getDayOfWeek(),
                        dto.getPeriodNumber()
                ))
                .map(timetableMapper::toEntity)
                .collect(Collectors.toList());

        List<Timetable> saved = timetableRepository.saveAll(entitiesToSave);

        log.info("Successfully created {} timetables", saved.size());

        return saved.stream()
                .map(timetableMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TimetableDto> getTeacherTimetable(String teacherCode) {
        return timetableRepository.findByTeacherCode(teacherCode)
                .stream()
                .map(timetableMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TimetableDto> getTimetableForClass(String gradeLevel, String section, String dayOfWeek) {
        return timetableRepository.findByGradeLevelAndSectionAndDayOfWeek(gradeLevel, section, dayOfWeek)
                .stream()
                .map(timetableMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TimetableDto> getAllTimetables() {
        return timetableRepository.findAll()
                .stream()
                .map(timetableMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public String deleteTimetableById(Long id) {
        Optional<Timetable> timetableOpt = timetableRepository.findById(id);
        if (timetableOpt.isEmpty()) {
            throw new UserException("Timetable not found", HttpStatus.NOT_FOUND);
        }

        timetableRepository.deleteById(id);
        log.info("Deleted timetable with ID: {}", id);
        return "Deleted successfully";
    }
}
