package com.school.midland.userservice.service;

import com.school.midland.userservice.dto.TimetableSlotRequestDTO;
import com.school.midland.userservice.dto.TimetableSlotResponseDTO;
import com.school.midland.userservice.model.TimetableSlot;
import com.school.midland.userservice.repository.TimetableSlotRepository;
import lombok.AllArgsConstructor;
import lombok.Generated;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TimetableSlotService {
    private final TimetableSlotRepository timetableSlotRepository;
    private final SectionService sectionService;
    private final SubjectService subjectService;
    private final TeacherService teacherService;

    public void createTimetableSlot(TimetableSlotRequestDTO dto) {
        TimetableSlot slot= new TimetableSlot();
        slot.setId(UUID.randomUUID().toString());
        slot.setSection(sectionService.getSectionById(dto.sectionId()));
        slot.setSubject(subjectService.getSubjectById(dto.subjectId()));
        slot.setTeacher(teacherService.getTeacherById(dto.teacherId()));
        slot.setStartTime(LocalTime.parse(dto.startTime()));
        slot.setEndTime(LocalTime.parse(dto.endTime()));
        slot.setDayOfWeek(DayOfWeek.valueOf(dto.dayOfWeek().toUpperCase()));
        timetableSlotRepository.save(slot);
    }
    public List<TimetableSlotResponseDTO> getAllTimetableSlots() {
        return timetableSlotRepository.findAll()
                .stream()
                .map(TimetableSlotResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

}
