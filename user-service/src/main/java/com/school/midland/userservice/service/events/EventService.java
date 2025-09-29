package com.school.midland.userservice.service.events;

import com.school.midland.commonlib.dtos.EventDto;
import com.school.midland.userservice.models.Events;
import jakarta.validation.OverridesAttribute;

import java.util.List;

public interface EventService {
    Events createEvent(EventDto dto);
    List<Events> getAllEvents();
    Events updateEvent(Long id, EventDto dto);
    void deleteEvent(Long id);
}
