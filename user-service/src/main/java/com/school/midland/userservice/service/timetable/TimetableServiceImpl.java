package com.school.midland.userservice.service.timetable;

import com.school.midland.commonlib.dtos.TimetableDto;
import com.school.midland.commonlib.exception.UserException;
import com.school.midland.userservice.mappers.TimetableMapper;
import com.school.midland.userservice.models.Timetable;
import com.school.midland.userservice.repository.TimetableRepository;
import lombok.RequiredArgsConstructor;
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

    @Override
    public TimetableDto createTimetable(TimetableDto timetableDto) {
        boolean exists = timetableRepository.existsByGradeLevelAndSectionAndDayOfWeekAndPeriodNumber(
                timetableDto.getGradeLevel(),
                timetableDto.getSection(),
                timetableDto.getDayOfWeek(),
                timetableDto.getPeriodNumber()
        );
        timetableDto.setCreatedAt(LocalDateTime.now());
        timetableDto.setUpdatedAt(LocalDateTime.now());

        if (exists) {
            throw new RuntimeException("Timetable slot already exists for this class and period.");
        }

        Timetable saved = timetableRepository.save(timetableMapper.toEntity(timetableDto));
        return timetableMapper.toDto(saved);
    }

    @Override
    public List<TimetableDto> createTimetables(List<TimetableDto> timetableDtos) {
        System.out.println(timetableDtos);
        List<Timetable> filtered = timetableDtos.stream()
                .filter(dto -> !timetableRepository.existsByGradeLevelAndSectionAndDayOfWeekAndPeriodNumber(
                        dto.getGradeLevel(),
                        dto.getSection(),
                        dto.getDayOfWeek(),
                        dto.getPeriodNumber()
                ))
                .map(timetableMapper::toEntity)
                .peek(dto -> {

                    dto.setCreatedAt(LocalDateTime.now());
                    dto.setUpdatedAt(LocalDateTime.now());
                })

                .collect(Collectors.toList());

        System.out.println(filtered);

        List<Timetable> saved = timetableRepository.saveAll(filtered);
        return saved.stream().map(timetableMapper::toDto).collect(Collectors.toList());
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
           throw  new UserException("tiemtable not found", HttpStatus.BAD_REQUEST);
        }

        timetableRepository.deleteById(id);
        return "Deleted successfully";
    }
}