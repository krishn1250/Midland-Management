package com.school.midland.userservice.repository;

import com.school.midland.userservice.models.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByTeacherCodeAndAttendanceDate(String teacherCode, LocalDate attendanceDate);

    List<Attendance> findByAdmissionNumberAndAcademicYear(String admissionNumber, String academicYear);
    List<Attendance> findByAdmissionNumberAndAttendanceDate(String admissionNumber, LocalDate attendanceDate);
    List<Attendance> findByGradeLevelAndSectionAndAcademicYearAndAttendanceDate(
            String gradeLevel, String section, String academicYear, LocalDate date);
    Optional<Attendance> findByAttendanceDateAndPeriodNumberAndStudentUidAndGradeLevelAndSectionAndAcademicYear(
            LocalDate attendanceDate,
            Integer periodNumber,
            UUID studentUid,
            String gradeLevel,
            String section,
            String academicYear
    );
    Optional<Attendance>  findByAdmissionNumberAndAttendanceDateAndPeriodNumber(String admissionNumber,LocalDate attendanceDate,Integer periodNumber );
    boolean existsByAdmissionNumberAndAttendanceDateAndPeriodNumber(String admissionNumber, LocalDate attendanceDate, int periodNumber);
}
