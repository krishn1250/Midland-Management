package com.school.midland.user.controller;

import com.school.midland.commonlib.dtos.EventDto;
import com.school.midland.userservice.models.Events;
import com.school.midland.userservice.service.events.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventsController {
    private final EventService service;

    @PostMapping
    public Events create(@RequestBody EventDto dto) { return service.createEvent(dto); }
    @GetMapping
    public List<Events> getAll() { return service.getAllEvents(); }
    @PutMapping("/{id}") public Events update(@PathVariable Long id, @RequestBody EventDto dto) { return service.updateEvent(id, dto); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.deleteEvent(id); }
}
