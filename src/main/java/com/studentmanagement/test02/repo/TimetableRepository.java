package com.studentmanagement.test02.repo;

import com.studentmanagement.test02.model.Section;
import com.studentmanagement.test02.model.Teacher;
import com.studentmanagement.test02.model.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

// TimetableRepository.java
@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    // Get timetable for a specific section and day
    List<Timetable> findBySectionAndDayOfWeekOrderByStartTime(Section section, DayOfWeek dayOfWeek);

    // Get timetable for a specific teacher and day
    List<Timetable> findByTeacherAndDayOfWeekOrderByStartTime(Teacher teacher, DayOfWeek dayOfWeek);

    // Get all classes for a section
    List<Timetable> findBySectionOrderByDayOfWeekAscStartTimeAsc(Section section);

    // Get all classes taught by a teacher
    List<Timetable> findByTeacherOrderByDayOfWeekAscStartTimeAsc(Teacher teacher);

    // Find timetable for attendance marking
    @Query("SELECT t FROM Timetable t JOIN FETCH t.section JOIN FETCH t.subject " +
            "WHERE t.section.sectionId = :sectionId AND t.subject.subjectId = :subjectId " +
            "AND t.dayOfWeek = :dayOfWeek AND t.startTime <= :currentTime AND t.endTime >= :currentTime")
    Optional<Timetable> findCurrentClass(@Param("sectionId") Long sectionId,
                                         @Param("subjectId") Long subjectId,
                                         @Param("dayOfWeek") DayOfWeek dayOfWeek,
                                         @Param("currentTime") LocalTime currentTime);
}