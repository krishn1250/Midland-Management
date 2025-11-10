package com.school.midland.user.service.events;

import com.school.midland.commonlib.dtos.EventDto;
import com.school.midland.userservice.models.Events;

import java.util.List;

public interface EventService {
    Events createEvent(EventDto dto);
    List<Events> getAllEvents();
    Events updateEvent(Long id, EventDto dto);
    void deleteEvent(Long id);
}
