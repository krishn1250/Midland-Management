package com.school.midland.userservice.service.attendance;

import com.school.midland.commonlib.dtos.AttendanceDto;
import com.school.midland.commonlib.exception.UserException;
import com.school.midland.userservice.dto.MarkAttendanceRequest;
import com.school.midland.userservice.dto.StudentAttendanceEntry;
import com.school.midland.userservice.mappers.AttendanceMapper;
import com.school.midland.userservice.models.Attendance;
import com.school.midland.userservice.models.Student;
import com.school.midland.userservice.repository.AttendanceRepository;
import com.school.midland.userservice.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public void markAttendance(MarkAttendanceRequest request) {

        List<StudentAttendanceEntry> entries = request.getAttendanceList();
        if (entries == null || entries.isEmpty()) {
            throw new UserException("Attendance list cannot be empty.", HttpStatus.BAD_REQUEST);
        }

        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);

        for (StudentAttendanceEntry entry : entries) {

            if (request.getAttendanceDate().isBefore(oneMonthAgo)) {
                throw new UserException(
                        "Attendance for date " + request.getAttendanceDate() + " is locked. Cannot update older than 1 month.",
                        HttpStatus.FORBIDDEN
                );
            }

            // ✅ Lookup student UID from repository
            UUID studentUid = studentRepository.findBySchoolEmail(entry.getEmail())
                    .map(Student::getStudentUid)
                    .orElseThrow(() -> new UserException(
                            "Student not found for email: " + entry.getEmail(),
                            HttpStatus.BAD_REQUEST
                    ));

            // Check if attendance already exists
            Optional<Attendance> existing = attendanceRepository
                    .findByAdmissionNumberAndAttendanceDateAndPeriodNumber(
                            entry.getAdmissionNumber(),
                            request.getAttendanceDate(),
                            request.getPeriodNumber()
                    );

            if (existing.isPresent()) {
                Attendance existingStudent = existing.get();
                if (!existingStudent.getStatus().name().equals(entry.getStatus())
                        || !Objects.equals(existingStudent.getRemarks(), entry.getRemarks())) {

                    existingStudent.setStatus(Attendance.AttendanceStatus.valueOf(entry.getStatus()));
                    existingStudent.setRemarks(entry.getRemarks());
                    existingStudent.setUpdatedBy(request.getCreatedBy());
                    existingStudent.setIsEditable(true);
                    existingStudent.setUpdatedAt(LocalDateTime.now());
                    attendanceRepository.save(existingStudent);
                }
            } else {
                // Create new attendance record
                Attendance newRecord = Attendance.builder()
                        .studentUid(studentUid)
                        .schoolEmail(entry.getEmail())
                        .admissionNumber(entry.getAdmissionNumber())
                        .gradeLevel(request.getGradeLevel())
                        .section(request.getSection())
                        .academicYear(request.getAcademicYear())
                        .attendanceDate(request.getAttendanceDate())
                        .periodNumber(request.getPeriodNumber())
                        .subjectCode(request.getSubjectCode())
                        .subjectName(request.getSubjectName())
                        .teacherCode(request.getTeacherCode())
                        .status(Attendance.AttendanceStatus.valueOf(entry.getStatus()))
                        .remarks(entry.getRemarks())
                        .isEditable(true)
                        .createdBy(request.getCreatedBy())
                        .updatedBy(request.getCreatedBy())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();

                attendanceRepository.save(newRecord);
            }
        }
    }

    @Override
    public List<AttendanceDto> getAttendanceForTeacher(String teacherCode, LocalDate date) {
        return attendanceRepository.findByTeacherCodeAndAttendanceDate(teacherCode, date)
                .stream()
                .map(AttendanceMapper::toDto)
                .toList();
    }

    @Override
    public List<AttendanceDto> getAttendanceForStudent(String admissionNumber, String academicYear) {
        return attendanceRepository.findByAdmissionNumberAndAcademicYear(admissionNumber, academicYear)
                .stream()
                .map(AttendanceMapper::toDto)
                .toList();
    }

    @Override
    public List<AttendanceDto> getAttendanceByClassAndDate(String gradeLevel, String section, String academicYear, LocalDate date) {
        return attendanceRepository.findByGradeLevelAndSectionAndAcademicYearAndAttendanceDate(
                        gradeLevel, section, academicYear, date)
                .stream()
                .map(AttendanceMapper::toDto)
                .toList();
    }
}
