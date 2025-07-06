package com.studentmanagement.test02.repo;

import com.studentmanagement.test02.model.Attendance;
import com.studentmanagement.test02.model.Student;
import com.studentmanagement.test02.model.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// AttendanceRepository.java
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // Get attendance for a specific student on a specific date
    @Query("SELECT a FROM Attendance a JOIN FETCH a.timetable t JOIN FETCH t.subject s " +
            "WHERE a.student.suuid = :studentUuid AND a.date = :date")
    List<Attendance> findAttendanceByStudentAndDate(@Param("studentUuid") String studentUuid,
                                                    @Param("date") LocalDate date);

    // Get all attendance records for a student
    List<Attendance> findByStudentSuuid(String studentUuid);

    // Get attendance stats for a student
    @Query("SELECT COUNT(a) as totalClasses, " +
            "COUNT(CASE WHEN a.status = 'PRESENT' THEN 1 END) as presentCount, " +
            "COUNT(CASE WHEN a.status = 'LATE' THEN 1 END) as lateCount, " +
            "COUNT(CASE WHEN a.status = 'ABSENT' THEN 1 END) as absentCount " +
            "FROM Attendance a WHERE a.student.suuid = :studentUuid")
    AttendanceStatsProjection getAttendanceStats(@Param("studentUuid") String studentUuid);

    // Get subject-wise attendance for a student
    @Query("SELECT s.subjectName as subjectName, " +
            "COUNT(a) as totalClasses, " +
            "COUNT(CASE WHEN a.status = 'PRESENT' THEN 1 END) as presentCount " +
            "FROM Attendance a JOIN a.timetable t JOIN t.subject s " +
            "WHERE a.student.suuid = :studentUuid " +
            "GROUP BY s.subjectId, s.subjectName")
    List<SubjectAttendanceProjection> getSubjectWiseAttendance(@Param("studentUuid") String studentUuid);

    // Get attendance for a specific timetable and date (for teachers to mark attendance)
    List<Attendance> findByTimetableAndDate(Timetable timetable, LocalDate date);

    // Check if attendance is already marked for a specific class
    boolean existsByTimetableAndDateAndStudent(Timetable timetable, LocalDate date, Student student);
}