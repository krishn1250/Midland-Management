package com.school.midland.user.repository;

import com.school.midland.user.models.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // 🔹 Teacher-based fetch with pagination
    Page<Attendance> findByTeacherCodeAndAttendanceDate(String teacherCode, LocalDate attendanceDate, Pageable pageable);

    // 🔹 Student-based fetch
    List<Attendance> findByAdmissionNumberAndAcademicYear(String admissionNumber, String academicYear);

    List<Attendance> findByAdmissionNumberAndAttendanceDate(String admissionNumber, LocalDate attendanceDate);

    // 🔹 Class & Date based fetch
    List<Attendance> findByGradeLevelAndSectionAndAcademicYearAndAttendanceDate(
            String gradeLevel,
            String section,
            String academicYear,
            LocalDate attendanceDate
    );

    // 🔹 For unique identification during marking
    Optional<Attendance> findByAdmissionNumberAndAttendanceDateAndPeriodNumber(
            String admissionNumber,
            LocalDate attendanceDate,
            Integer periodNumber
    );

    boolean existsByAdmissionNumberAndAttendanceDateAndPeriodNumber(
            String admissionNumber,
            LocalDate attendanceDate,
            int periodNumber
    );

    // 🔹 Defensive lookup for advanced validation (by UID)
    Optional<Attendance> findByAttendanceDateAndPeriodNumberAndStudentUidAndGradeLevelAndSectionAndAcademicYear(
            LocalDate attendanceDate,
            Integer periodNumber,
            UUID studentUid,
            String gradeLevel,
            String section,
            String academicYear
    );

    // 🔹 Flexible search for analytics, dashboards, etc.
    @Query("""
        SELECT a FROM Attendance a 
        WHERE (:gradeLevel IS NULL OR a.gradeLevel = :gradeLevel)
          AND (:section IS NULL OR a.section = :section)
          AND (:teacherCode IS NULL OR a.teacherCode = :teacherCode)
          AND (:academicYear IS NULL OR a.academicYear = :academicYear)
          AND (:attendanceDate IS NULL OR a.attendanceDate = :attendanceDate)
    """)
    Page<Attendance> searchAttendance(
            @Param("gradeLevel") String gradeLevel,
            @Param("section") String section,
            @Param("teacherCode") String teacherCode,
            @Param("academicYear") String academicYear,
            @Param("attendanceDate") LocalDate attendanceDate,
            Pageable pageable
    );
}
