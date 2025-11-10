package com.school.midland.user.service.timetable;



import com.school.midland.user.dto.TimetableDto;

import java.util.List;

public interface TimetableService {
    TimetableDto createTimetable(TimetableDto timetableDto);
    TimetableDto updateTimetable(String timetableCode, TimetableDto timetableDto);
    List<TimetableDto> createTimetables(List<TimetableDto> timetableDtos);
    List<TimetableDto> getTeacherTimetable(String teacherCode);
    List<TimetableDto> getTimetableForClass(String gradeLevel, String section, String dayOfWeek);
    List<TimetableDto> getAllActiveTimetables();
    String deactivateTimetable(String timetableCode);
}
