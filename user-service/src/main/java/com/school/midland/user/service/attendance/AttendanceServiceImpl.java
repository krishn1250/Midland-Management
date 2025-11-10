package com.school.midland.user.service.attendance;

import com.school.midland.user.dto.PageResponse;
import com.school.midland.user.dto.attendance.*;
import com.school.midland.user.exception.UserException;
import com.school.midland.user.mappers.AttendanceMapper;
import com.school.midland.user.models.Attendance;
import com.school.midland.user.models.Student;
import com.school.midland.user.repository.AttendanceRepository;
import com.school.midland.user.repository.StudentRepository;
import com.school.midland.user.util.ValidatorUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final ValidatorUtils validatorUtils;

    private static final int BATCH_SIZE = 200;

    // ✅ Bulk mark or update attendance
    @Override
    @Transactional
    public MarkAttendanceResponse markAttendance(MarkAttendanceRequest request) {
        validatorUtils.validateField(request.getGradeLevel(), "gradeLevel");
        validatorUtils.validateField(request.getSection(), "section");
        validatorUtils.validateField(request.getAcademicYear(), "academicYear");
        Objects.requireNonNull(request.getAttendanceDate(), "attendanceDate is required");

        List<StudentAttendanceEntry> entries = request.getResults()
                .stream().map(result -> {
                    StudentAttendanceEntry e = new StudentAttendanceEntry();
                    e.setAdmissionNumber(result.getAdmissionNumber());
                    e.setStatus(result.getStatus());
                    e.setRemarks(result.getMessage());
                    return e;
                }).toList();

        List<PerStudentResult> results = new ArrayList<>(entries.size());
        List<Attendance> toSave = new ArrayList<>();

        // 🔹 Fetch all students for this batch once
        Set<String> admissionNumbers = entries.stream()
                .map(StudentAttendanceEntry::getAdmissionNumber)
                .collect(Collectors.toSet());

        List<Student> students = studentRepository.findByAdmissionNumberIn(new ArrayList<>(admissionNumbers));
        Map<String, Student> studentMap = students.stream()
                .collect(Collectors.toMap(Student::getAdmissionNumber, s -> s));

        for (StudentAttendanceEntry entry : entries) {
            String admission = entry.getAdmissionNumber();
            Student student = studentMap.get(admission);
            if (student == null) {
                results.add(new PerStudentResult(admission, "FAILED", "Student not found"));
                continue;
            }

            try {
                Optional<Attendance> existingOpt =
                        attendanceRepository.findByAdmissionNumberAndAttendanceDateAndPeriodNumber(
                                admission,
                                request.getAttendanceDate(),
                                request.getPeriodNumber()
                        );

                if (existingOpt.isPresent()) {
                    Attendance existing = existingOpt.get();
                    if (Boolean.FALSE.equals(existing.getIsEditable())) {
                        results.add(new PerStudentResult(admission, "SKIPPED", "Attendance locked"));
                        continue;
                    }

                    boolean changed = false;
                    if (!existing.getStatus().name().equalsIgnoreCase(entry.getStatus())) {
                        existing.setStatus(Attendance.AttendanceStatus.valueOf(entry.getStatus().toUpperCase()));
                        changed = true;
                    }
                    if (!Objects.equals(existing.getRemarks(), entry.getRemarks())) {
                        existing.setRemarks(entry.getRemarks());
                        changed = true;
                    }
                    if (changed) {
                        existing.setUpdatedBy(request.getCreatedBy());
                        existing.setUpdatedAt(LocalDateTime.now());
                        toSave.add(existing);
                        results.add(new PerStudentResult(admission, "UPDATED", "Updated successfully"));
                    } else {
                        results.add(new PerStudentResult(admission, "SKIPPED", "No change"));
                    }
                } else {
                    Attendance newRecord = Attendance.builder()
                            .studentUid(student.getStudentUid())
                            .admissionNumber(admission)
                            .gradeLevel(request.getGradeLevel())
                            .section(request.getSection())
                            .academicYear(request.getAcademicYear())
                            .attendanceDate(request.getAttendanceDate())
                            .periodNumber(request.getPeriodNumber())
                            .subjectCode(request.getSubjectCode())
                            .subjectName(request.getSubjectName())
                            .teacherCode(request.getTeacherCode())
                            .status(Attendance.AttendanceStatus.valueOf(entry.getStatus().toUpperCase()))
                            .remarks(entry.getRemarks())
                            .isEditable(true)
                            .createdBy(request.getCreatedBy())
                            .updatedBy(request.getCreatedBy())
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build();

                    toSave.add(newRecord);
                    results.add(new PerStudentResult(admission, "CREATED", "New record created"));
                }

                if (toSave.size() >= BATCH_SIZE) {
                    flushBatch(toSave);
                }

            } catch (Exception ex) {
                log.error("Error processing {}: {}", admission, ex.getMessage());
                results.add(new PerStudentResult(admission, "FAILED", ex.getMessage()));
            }
        }

        if (!toSave.isEmpty()) flushBatch(toSave);

        // 🔹 Build response
        MarkAttendanceResponse response = new MarkAttendanceResponse();
        response.setGradeLevel(request.getGradeLevel());
        response.setSection(request.getSection());
        response.setAcademicYear(request.getAcademicYear());
        response.setAttendanceDate(request.getAttendanceDate());
        response.setPeriodNumber(request.getPeriodNumber());
        response.setSubjectName(request.getSubjectName());
        response.setTeacherCode(request.getTeacherCode());
        response.setCreatedBy(request.getCreatedBy());
        response.setAttendanceList(entries);

        return response;
    }

    private void flushBatch(List<Attendance> batch) {
        try {
            attendanceRepository.saveAll(batch);
            attendanceRepository.flush();
        } catch (DataIntegrityViolationException ex) {
            log.warn("Batch save conflict, retrying individually");
            for (Attendance a : batch) {
                try {
                    attendanceRepository.saveAndFlush(a);
                } catch (Exception e) {
                    log.warn("Skipping duplicate entry for {}", a.getAdmissionNumber());
                }
            }
        } finally {
            batch.clear();
        }
    }

    // ✅ Teacher Attendance
    @Override
    public PageResponse<AttendanceDto> getAttendanceForTeacher(String teacherCode, LocalDate date, int page, int size) {
        validatorUtils.validateField(teacherCode, "teacherCode");
        Pageable pageable = PageRequest.of(page, size, Sort.by("admissionNumber").ascending());
        Page<Attendance> attendancePage = attendanceRepository.findByTeacherCodeAndAttendanceDate(teacherCode, date, pageable);
        List<AttendanceDto> dtos = attendancePage.getContent().stream().map(AttendanceMapper::toDto).toList();
        return new PageResponse<>(dtos, attendancePage.getTotalElements(), page, size, attendancePage.getTotalPages());
    }

    // ✅ Student Attendance
    @Override
    public List<AttendanceDto> getAttendanceForStudent(String admissionNumber, String academicYear) {
        validatorUtils.validateField(admissionNumber, "admissionNumber");
        validatorUtils.validateField(academicYear, "academicYear");
        return attendanceRepository.findByAdmissionNumberAndAcademicYear(admissionNumber, academicYear)
                .stream().map(AttendanceMapper::toDto).toList();
    }

    // ✅ Class Attendance
    @Override
    public List<AttendanceDto> getAttendanceByClassAndDate(String gradeLevel, String section, String academicYear, LocalDate date) {
        validatorUtils.validateField(gradeLevel, "gradeLevel");
        validatorUtils.validateField(section, "section");
        validatorUtils.validateField(academicYear, "academicYear");
        return attendanceRepository.findByGradeLevelAndSectionAndAcademicYearAndAttendanceDate(
                        gradeLevel, section, academicYear, date)
                .stream().map(AttendanceMapper::toDto).toList();
    }

    // ✅ Edit Attendance
    @Override
    @Transactional
    public AttendanceDto editAttendance(Long attendanceId, String status, String remarks, String updatedBy) {
        if (attendanceId == null) throw new UserException("attendanceId required", HttpStatus.BAD_REQUEST);
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new UserException("Attendance not found", HttpStatus.NOT_FOUND));

        if (Boolean.FALSE.equals(attendance.getIsEditable()))
            throw new UserException("Attendance not editable", HttpStatus.FORBIDDEN);

        try {
            Attendance.AttendanceStatus newStatus = Attendance.AttendanceStatus.valueOf(status.toUpperCase());
            attendance.setStatus(newStatus);
        } catch (Exception ex) {
            throw new UserException("Invalid status: " + status, HttpStatus.BAD_REQUEST);
        }

        attendance.setRemarks(remarks);
        attendance.setUpdatedBy(updatedBy);
        attendance.setUpdatedAt(LocalDateTime.now());
        return AttendanceMapper.toDto(attendanceRepository.save(attendance));
    }
}
