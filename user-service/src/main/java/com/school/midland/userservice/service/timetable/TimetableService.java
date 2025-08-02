package com.school.midland.userservice.service.timetable;

import com.school.midland.commonlib.dtos.TimetableDto;

import java.util.List;

public interface TimetableService {
    TimetableDto createTimetable(TimetableDto timetableDto);
    List<TimetableDto>  createTimetables(List<TimetableDto> timetableDto);
    List<TimetableDto> getTeacherTimetable(String teacherCode);

    List<TimetableDto> getTimetableForClass(String gradeLevel, String section, String dayOfWeek);

    List<TimetableDto> getAllTimetables();
    String deleteTimetableById(Long id);
}
