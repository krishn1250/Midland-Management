package com.studentmanagement.test02.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "timetable_id", nullable = false)
    private Timetable timetable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_uuid", referencedColumnName = "suuid")
    private Student student;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttendanceStatus status;

    @Column(name = "marked_by_teacher_uuid")
    private String markedByTeacherUuid;

    @Column(name = "timestamp_marked")
    private LocalDateTime timestampMarked;

    private String remarks;

    // Constructors
    public Attendance() {}

    public Attendance(Timetable timetable, Student student, LocalDate date,
                      AttendanceStatus status, String markedByTeacherUuid, LocalDateTime timestampMarked) {
        this.timetable = timetable;
        this.student = student;
        this.date = date;
        this.status = status;
        this.markedByTeacherUuid = markedByTeacherUuid;
        this.timestampMarked = timestampMarked;
    }

    // Getters and setters
    public Long getAttendanceId() { return attendanceId; }
    public void setAttendanceId(Long attendanceId) { this.attendanceId = attendanceId; }

    public Timetable getTimetable() { return timetable; }
    public void setTimetable(Timetable timetable) { this.timetable = timetable; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public AttendanceStatus getStatus() { return status; }
    public void setStatus(AttendanceStatus status) { this.status = status; }

    public String getMarkedByTeacherUuid() { return markedByTeacherUuid; }
    public void setMarkedByTeacherUuid(String markedByTeacherUuid) { this.markedByTeacherUuid = markedByTeacherUuid; }

    public LocalDateTime getTimestampMarked() { return timestampMarked; }
    public void setTimestampMarked(LocalDateTime timestampMarked) { this.timestampMarked = timestampMarked; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}

