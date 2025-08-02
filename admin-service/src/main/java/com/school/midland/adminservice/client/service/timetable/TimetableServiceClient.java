package com.school.midland.adminservice.client.service.timetable;

import com.school.midland.commonlib.dtos.TimetableDto;

import java.sql.Time;
import java.util.List;

public interface TimetableServiceClient {
    TimetableDto createTimetable(TimetableDto timetableDto);
    List<TimetableDto> createTimetables(List<TimetableDto> timetableDtos);
    List<TimetableDto> getTimetableByTeacherCode(String teacherCode);
    List<TimetableDto> getTimetableForClass(String gradeLevel, String section, String dayOfWeek);
    List<TimetableDto> getAllTimetables();
    String deleteTimetableById(Long id);
}
