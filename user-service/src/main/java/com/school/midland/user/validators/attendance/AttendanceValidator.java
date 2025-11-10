package com.school.midland.user.validators.attendance;

import com.school.midland.user.dto.attendance.MarkAttendanceRequest;
import com.school.midland.user.exception.UserException;
import com.school.midland.user.repository.TimetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AttendanceValidator {

    private final TimetableRepository timetableRepository;

    public void validateMarkRequest(MarkAttendanceRequest request) {
        if (request == null) throw new UserException("Request cannot be null", HttpStatus.BAD_REQUEST);
        if (request.getGradeLevel() == null) throw new UserException("Grade level is required", HttpStatus.BAD_REQUEST);
        if (request.getSection() == null) throw new UserException("Section is required", HttpStatus.BAD_REQUEST);
        if (request.getAcademicYear() == null) throw new UserException("Academic year is required", HttpStatus.BAD_REQUEST);
        if (request.getAttendanceDate() == null) throw new UserException("Attendance date is required", HttpStatus.BAD_REQUEST);
        if (request.getPeriodNumber() == null) throw new UserException("Period number is required", HttpStatus.BAD_REQUEST);
        if (request.getTeacherCode() == null) throw new UserException("Teacher code is required", HttpStatus.BAD_REQUEST);
        if (request.getResults() == null || request.getResults().isEmpty())
            throw new UserException("Attendance list is empty", HttpStatus.BAD_REQUEST);
    }

    public void verifyTeacherForSlot(String teacherCode, String gradeLevel, String section,
                                     Integer periodNumber, String subjectCode, LocalDate date) {

        boolean assigned = timetableRepository.existsByTeacherCodeAndGradeLevelAndSectionAndPeriodNumberAndSubjectCodeAndIsActiveTrue(
                teacherCode, gradeLevel, section, periodNumber, subjectCode);

        if (!assigned) {
            throw new UserException("Teacher not assigned to this period slot", HttpStatus.FORBIDDEN);
        }

        if (Objects.requireNonNull(date).isAfter(LocalDate.now())) {
            throw new UserException("Cannot mark attendance for a future date", HttpStatus.BAD_REQUEST);
        }
    }
}
