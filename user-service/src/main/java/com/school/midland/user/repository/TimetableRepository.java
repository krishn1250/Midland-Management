package com.school.midland.user.repository;

import com.school.midland.user.models.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    boolean existsByGradeLevelAndSectionAndDayOfWeekAndPeriodNumberAndIsActiveTrue(
            String gradeLevel, String section, String dayOfWeek, Integer periodNumber
    );

    Optional<Timetable> findByTimetableCode(String timetableCode);

    List<Timetable> findByTeacherCodeAndIsActiveTrue(String teacherCode);

    List<Timetable> findByGradeLevelAndSectionAndDayOfWeekAndIsActiveTrue(
            String gradeLevel, String section, String dayOfWeek
    );

    List<Timetable> findByIsActiveTrue();

    boolean existsByGradeLevelAndSectionAndDayOfWeekAndPeriodNumberAndTeacherCodeAndSubjectCode(String gradeLevel, String section, String dayOfWeek, Integer periodNumber, String teacherCode, String subjectCode);

    boolean existsByTeacherCodeAndGradeLevelAndSectionAndPeriodNumberAndSubjectCodeAndIsActiveTrue(String teacherCode, String gradeLevel, String section, Integer periodNumber, String subjectCode);
}
