// --- File: service/AttendanceService.java ---
package com.school.midland.userservice.service;

import com.school.midland.userservice.dto.AttendanceRequestDTO;
import com.school.midland.userservice.model.AttendanceRecord;
import com.school.midland.userservice.model.Student;
import com.school.midland.userservice.model.TimetableSlot;
import com.school.midland.userservice.repository.AttendanceRepository;
import com.school.midland.userservice.repository.StudentRepository;
import com.school.midland.userservice.repository.TimetableSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate; // <-- Import
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final TimetableSlotRepository timetableSlotRepository;

    public void markAttendance(AttendanceRequestDTO request) {
        Student student = studentRepository.findById(request.studentId())
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + request.studentId()));

        TimetableSlot timetableSlot = timetableSlotRepository.findById(request.timetableSlotId())
                .orElseThrow(() -> new RuntimeException("Timetable Slot not found with id: " + request.timetableSlotId()));

        LocalDate classDate = LocalDate.parse(request.classDate(), DateTimeFormatter.ISO_LOCAL_DATE);

        // --- ADD THIS CRITICAL VALIDATION ---
        // Check if the day of the week for the given date matches the timetable slot's day.
        // e.g., if classDate is '2023-10-24' (a Tuesday), but the slot is for MONDAY, throw an error.
        if (classDate.getDayOfWeek() != timetableSlot.getDayOfWeek()) {
            throw new IllegalArgumentException(
                "The provided class date (" + classDate.getDayOfWeek() + ")" +
                " does not match the timetable slot's scheduled day (" + timetableSlot.getDayOfWeek() + ")."
            );
        }

        // Create the attendance record
        AttendanceRecord attendanceRecord = new AttendanceRecord();
        attendanceRecord.setId(UUID.randomUUID().toString());
        attendanceRecord.setStudent(student);
        attendanceRecord.setTimetableSlot(timetableSlot);
        attendanceRecord.setStatus(request.status());
        attendanceRecord.setRemarks(request.remarks());
        attendanceRecord.setRecordTimestamp(LocalDateTime.now()); // The moment the record is saved
        attendanceRecord.setClassDate(classDate); // The date the class actually occurred

        attendanceRepository.save(attendanceRecord);
    }
}