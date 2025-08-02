package com.school.midland.adminservice.service.timetable;

import com.school.midland.adminservice.client.service.timetable.TimetableServiceClient;
import com.school.midland.commonlib.dtos.TimetableDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimetableManageServiceImpl implements TimetableManageService{

    private final TimetableServiceClient timetableServiceClient;

    @Override
    public List<TimetableDto> createTimetables(List<TimetableDto> timetableDtos) {
        return timetableServiceClient.createTimetables(timetableDtos);
    }

    @Override
    public TimetableDto createTimetable(TimetableDto timetableDto) {
        return timetableServiceClient.createTimetable(timetableDto);
    }

    @Override
    public List<TimetableDto> fetchTeacherTimetable(String teacherCode) {
        return timetableServiceClient.getTimetableByTeacherCode(teacherCode);
    }

    @Override
    public List<TimetableDto> fetchClassTimetable(String grade, String section, String day) {
        return timetableServiceClient.getTimetableForClass(grade, section, day);
    }

    @Override
    public List<TimetableDto> fetchAllTimetables() {
        return timetableServiceClient.getAllTimetables();
    }

    @Override
    public String removeTimetable(Long id) {
        return timetableServiceClient.deleteTimetableById(id);
    }
}
