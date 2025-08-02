package com.school.midland.userservice.service.attendance;

import com.school.midland.commonlib.dtos.AttendanceDto;
import com.school.midland.commonlib.exception.UserException;
import com.school.midland.userservice.dto.MarkAttendanceRequest;
import com.school.midland.userservice.dto.MarkAttendanceResponse;
import com.school.midland.userservice.dto.StudentAttendanceEntry;
import com.school.midland.userservice.mappers.AttendanceMapper;
import com.school.midland.userservice.models.Attendance;
import com.school.midland.userservice.repository.AttendanceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class AttendanceServiceImpl implements  AttendanceService{

    private final AttendanceRepository attendanceRepository;

    @Override
    @Transactional
    public void markAttendance(MarkAttendanceRequest request) {
       List<StudentAttendanceEntry> entries=request.getAttendanceList();
       List<Attendance> studentMark=new ArrayList<>();
        if (entries == null || entries.isEmpty()) {
            throw new UserException("Attendance list cannot be empty.", HttpStatus.BAD_REQUEST);
        }
        for(StudentAttendanceEntry entry:entries){
            Optional<Attendance> existing = attendanceRepository
                    .findByAdmissionNumberAndAttendanceDateAndPeriodNumber(
                            entry.getAdmissionNumber(),
                            request.getAttendanceDate(),
                            request.getPeriodNumber()
                    );
            if (existing.isPresent()) {
                Attendance existingStudent = existing.get();
                if (!existingStudent.getStatus().equals(entry.getStatus())
                        || !Objects.equals(existingStudent.getRemarks(), entry.getRemarks())) {
                    existingStudent.setStatus(Attendance.AttendanceStatus.valueOf(entry.getStatus()));
                    existingStudent.setRemarks(entry.getRemarks());
                    existingStudent.setUpdatedBy(request.getCreatedBy());
                    existingStudent.setIsEditable(true);
                    existingStudent.setUpdatedAt(LocalDateTime.now());
                    attendanceRepository.save(existingStudent);

                }
            } else {
                Attendance newRecord = Attendance.builder()
                        .studentUid(entry.getStudentUid())
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
//                log.info("Created new attendance for {}", entry.getAdmissionNumber());
            }
        }
        }






    @Override
    public List<AttendanceDto> getAttendanceForTeacher(String teacherCode, LocalDate date) {
        return List.of();
    }

    @Override
    public List<AttendanceDto> getAttendanceForStudent(String admissionNumber, String academicYear) {
        return List.of();
    }

    @Override
    public List<AttendanceDto> getAttendanceByClassAndDate(String gradeLevel, String section, String academicYear, LocalDate date) {
        return List.of();
    }
}
