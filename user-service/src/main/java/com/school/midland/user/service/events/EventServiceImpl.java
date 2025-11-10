package com.school.midland.user.service.events;

import com.school.midland.commonlib.dtos.EventDto;
import com.school.midland.userservice.models.Events;
import com.school.midland.userservice.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository repo;

    public Events createEvent(EventDto dto) {
        Events e = Events.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .eventType(dto.getEventType())
                .targetAudience(dto.getTargetAudience())
                .eventDate(dto.getEventDate())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .location(dto.getLocation())
                .createdByTeacherCode(dto.getCreatedByTeacherCode())
                .build();
        return repo.save(e);
    }
    public List<Events> getAllEvents() { return repo.findAll(); }
    public Events updateEvent(Long id, EventDto dto) {
        return repo.findById(id).map(e -> {
            e.setTitle(dto.getTitle());
            e.setDescription(dto.getDescription());
            return repo.save(e);
        }).orElseThrow(() -> new RuntimeException("Event not found"));
    }
    public void deleteEvent(Long id) { repo.deleteById(id); }
}