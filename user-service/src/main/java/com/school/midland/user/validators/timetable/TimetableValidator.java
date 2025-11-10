package com.school.midland.user.utils.validators;


import com.school.midland.user.dto.TimetableDto;
import com.school.midland.user.exception.UserException;
import com.school.midland.user.repository.TimetableRepository;
import com.school.midland.user.util.ValidatorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TimetableValidator {

    private final ValidatorUtils validatorUtils;
    private final TimetableRepository timetableRepository;

    public void validateCreateRequest(TimetableDto dto) {
        validatorUtils.validateNonEmpty(dto.getGradeLevel(), "Grade level");
        validatorUtils.validateNonEmpty(dto.getSection(), "Section");
        validatorUtils.validateNonEmpty(dto.getDayOfWeek(), "Day of week");
        validatorUtils.validateNonNull(dto.getPeriodNumber(), "Period number");
        validatorUtils.validateNonEmpty(dto.getSubjectCode(), "Subject code");
        validatorUtils.validateNonEmpty(dto.getSubjectName(), "Subject name");
        validatorUtils.validateNonEmpty(dto.getTeacherCode(), "Teacher code");
        validatorUtils.validateNonEmpty(dto.getAcademicYear(), "Academic year");
        validatorUtils.validateNonNull(dto.getStartTime(), "Start time");
        validatorUtils.validateNonNull(dto.getEndTime(), "End time");

        // Business rule: one active timetable per class-section-day-period
        boolean existsActive = timetableRepository.existsByGradeLevelAndSectionAndDayOfWeekAndPeriodNumberAndIsActiveTrue(
                dto.getGradeLevel(), dto.getSection(), dto.getDayOfWeek(), dto.getPeriodNumber()
        );

        if (existsActive) {
            throw new UserException(
                    "An active timetable already exists for this class-section-day-period.",
                    HttpStatus.BAD_REQUEST
            );
        }

        // Ensure logical time range
        if (dto.getStartTime().isAfter(dto.getEndTime()) || dto.getStartTime().equals(dto.getEndTime())) {
            throw new UserException("Start time must be before end time.", HttpStatus.BAD_REQUEST);
        }
    }

    public void validateUpdateRequest(TimetableDto dto) {
        validatorUtils.validateNonEmpty(dto.getSubjectCode(), "Subject code");
        validatorUtils.validateNonEmpty(dto.getSubjectName(), "Subject name");
        validatorUtils.validateNonEmpty(dto.getTeacherCode(), "Teacher code");
        validatorUtils.validateNonNull(dto.getStartTime(), "Start time");
        validatorUtils.validateNonNull(dto.getEndTime(), "End time");

        if (dto.getStartTime().isAfter(dto.getEndTime()) || dto.getStartTime().equals(dto.getEndTime())) {
            throw new UserException("Start time must be before end time.", HttpStatus.BAD_REQUEST);
        }
    }
}
