package com.school.midland.adminservice.service.timetable;

import com.school.midland.commonlib.dtos.TimetableDto;

import java.util.List;

public interface TimetableManageService {

    public List<TimetableDto> createTimetables(List<TimetableDto> timetableDtos);
    public TimetableDto createTimetable(TimetableDto timetableDtos);
    public List<TimetableDto> fetchTeacherTimetable(String teacherCode);
    public List<TimetableDto> fetchClassTimetable(String grade, String section, String day);
    public List<TimetableDto> fetchAllTimetables();
    public String removeTimetable(Long id);
}
