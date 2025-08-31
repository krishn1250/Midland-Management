// EventService.java (full version in user-service/src/main/java/com/school/midland/userservice/service)
package com.school.midland.userservice.service;

import com.school.midland.userservice.dto.EventRequestDTO;
import com.school.midland.userservice.dto.EventResponseDTO;
import com.school.midland.userservice.model.Event;
import com.school.midland.userservice.model.Section;
import com.school.midland.userservice.repository.EventRepository;
import com.school.midland.userservice.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final SectionRepository sectionRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;  // For event transport

    @Transactional
    public EventResponseDTO createEvent(EventRequestDTO dto) {
        Event event = new Event();
        event.setId(UUID.randomUUID().toString());
        event.setTitle(dto.title());
        event.setDescription(dto.description());
        event.setStartTime(dto.startTime());
        event.setEndTime(dto.endTime());

        if (dto.sectionIds() != null) {
            List<Section> sections = sectionRepository.findAllById(dto.sectionIds());
            event.setSections(sections);
        }

        Event savedEvent = eventRepository.save(event);

        // Publish event to Kafka for notification
        kafkaTemplate.send("school-events-topic", "New event created: " + savedEvent.getTitle());

        return mapToResponse(savedEvent);
    }

    public List<EventResponseDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public EventResponseDTO getEventById(String id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + id));
        return mapToResponse(event);
    }

    @Transactional
    public EventResponseDTO updateEvent(String id, EventRequestDTO dto) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + id));

        event.setTitle(dto.title());
        event.setDescription(dto.description());
        event.setStartTime(dto.startTime());
        event.setEndTime(dto.endTime());

        if (dto.sectionIds() != null) {
            List<Section> sections = sectionRepository.findAllById(dto.sectionIds());
            event.setSections(sections);
        }

        Event updatedEvent = eventRepository.save(event);

        // Publish update to Kafka
        kafkaTemplate.send("school-events-topic", "Event updated: " + updatedEvent.getTitle());

        return mapToResponse(updatedEvent);
    }

    @Transactional
    public void deleteEvent(String id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + id));
        eventRepository.delete(event);

        // Publish deletion to Kafka
        kafkaTemplate.send("school-events-topic", "Event deleted: " + event.getTitle());
    }

    private EventResponseDTO mapToResponse(Event event) {
        return new EventResponseDTO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getStartTime(),
                event.getEndTime(),
                event.getSections().stream().map(Section::getId).toList()
        );
    }
}
