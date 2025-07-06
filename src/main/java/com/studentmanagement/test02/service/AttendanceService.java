package com.studentmanagement.test02.service;

import com.studentmanagement.test02.dto.AttendanceDTO;
import com.studentmanagement.test02.dto.AttendanceMarkDTO;
import com.studentmanagement.test02.dto.AttendanceStatsDTO;
import com.studentmanagement.test02.dto.SubjectAttendanceDTO;
import com.studentmanagement.test02.model.Attendance;
import com.studentmanagement.test02.model.AttendanceStatus;
import com.studentmanagement.test02.model.Student;
import com.studentmanagement.test02.model.Timetable;
import com.studentmanagement.test02.repo.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TimetableRepository timetableRepository;

    // Get attendance for a student on a specific date
    public List<AttendanceDTO> getStudentAttendanceForDate(String studentUuid, LocalDate date) {
        List<Attendance> attendanceList = attendanceRepository.findAttendanceByStudentAndDate(studentUuid, date);
        return attendanceList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get overall attendance statistics for a student
    public AttendanceStatsDTO getStudentAttendanceStats(String studentUuid) {
        AttendanceStatsProjection stats = attendanceRepository.getAttendanceStats(studentUuid);

        AttendanceStatsDTO dto = new AttendanceStatsDTO();
        dto.setStudentUuid(studentUuid);
        dto.setTotalClasses(stats.getTotalClasses());
        dto.setPresentCount(stats.getPresentCount());
        dto.setLateCount(stats.getLateCount());
        dto.setAbsentCount(stats.getAbsentCount());
        dto.setAttendancePercentage(stats.getAttendancePercentage());

        return dto;
    }

    // Get subject-wise attendance for a student
    public List<SubjectAttendanceDTO> getSubjectWiseAttendance(String studentUuid) {
        List<SubjectAttendanceProjection> projections = attendanceRepository.getSubjectWiseAttendance(studentUuid);
        return projections.stream()
                .map(proj -> {
                    SubjectAttendanceDTO dto = new SubjectAttendanceDTO();
                    dto.setSubjectName(proj.getSubjectName());
                    dto.setTotalClasses(proj.getTotalClasses());
                    dto.setPresentCount(proj.getPresentCount());
                    dto.setAttendancePercentage(proj.getAttendancePercentage());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // Mark attendance for a class
    public void markAttendance(Long timetableId, List<AttendanceMarkDTO> attendanceMarks, String teacherUuid) {
        Timetable timetable = timetableRepository.findById(timetableId)
                .orElseThrow(() -> new RuntimeException("Timetable not found"));

        LocalDate today = LocalDate.now();

        for (AttendanceMarkDTO mark : attendanceMarks) {
            // Check if attendance already marked
            Student student = studentRepository.findById(mark.getStudentUuid())
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            boolean alreadyMarked = attendanceRepository.existsByTimetableAndDateAndStudent(timetable, today, student);

            if (!alreadyMarked) {
                Attendance attendance = new Attendance();
                attendance.setTimetable(timetable);
                attendance.setStudent(student);
                attendance.setDate(today);
                attendance.setStatus(AttendanceStatus.valueOf(mark.getStatus()));
                attendance.setMarkedByTeacherUuid(teacherUuid);
                attendance.setTimestampMarked(LocalDateTime.now());
                attendance.setRemarks(mark.getRemarks());

                attendanceRepository.save(attendance);
            }
        }
    }

    private AttendanceDTO convertToDTO(Attendance attendance) {
        AttendanceDTO dto = new AttendanceDTO();
        dto.setAttendanceId(attendance.getAttendanceId());
        dto.setStudentName(attendance.getStudent().getName());
        dto.setSubjectName(attendance.getTimetable().getSubject().getSubjectName());
        dto.setDate(attendance.getDate());
        dto.setStatus(attendance.getStatus().toString());
        dto.setTimestampMarked(attendance.getTimestampMarked());
        dto.setRemarks(attendance.getRemarks());
        return dto;
    }
}