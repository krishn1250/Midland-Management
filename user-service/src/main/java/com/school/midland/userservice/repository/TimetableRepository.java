package com.school.midland.userservice.repository;

import com.school.midland.userservice.models.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    List<Timetable> findByTeacherCode(String teacherCode);

    List<Timetable> findByGradeLevelAndSectionAndDayOfWeek(String gradeLevel, String section, String dayOfWeek);

    boolean existsByGradeLevelAndSectionAndDayOfWeekAndPeriodNumber(
            String gradeLevel, String section, String dayOfWeek, Integer periodNumber
    );
}
