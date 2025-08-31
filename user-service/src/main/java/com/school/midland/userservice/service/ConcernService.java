// ConcernService.java (in user-service/src/main/java/com/school/midland/userservice/service)
package com.school.midland.userservice.service;

import com.school.midland.userservice.dto.ConcernRequestDTO;
import com.school.midland.userservice.dto.ConcernResponseDTO;
import com.school.midland.userservice.model.Concern;
import com.school.midland.userservice.model.Student;
import com.school.midland.userservice.repository.ConcernRepository;
import com.school.midland.userservice.repository.StudentRepository;  // Assume this exists from your core entities
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConcernService {
    private final ConcernRepository concernRepository;
    private final StudentRepository studentRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public ConcernResponseDTO createConcern(ConcernRequestDTO dto) {
        Student student = studentRepository.findById(dto.studentId())
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + dto.studentId()));

        Concern concern = new Concern();
        concern.setId(UUID.randomUUID().toString());
        concern.setDescription(dto.description());
        concern.setSubmittedAt(LocalDateTime.now());
        concern.setStatus(dto.status() != null ? dto.status() : "OPEN");
        concern.setStudent(student);

        Concern savedConcern = concernRepository.save(concern);

        kafkaTemplate.send("school-events-topic", "New concern raised: " + savedConcern.getId());

        return mapToResponse(savedConcern);
    }

    public List<ConcernResponseDTO> getAllConcerns() {
        return concernRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ConcernResponseDTO getConcernById(String id) {
        Concern concern = concernRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Concern not found with ID: " + id));
        return mapToResponse(concern);
    }

    @Transactional
    public ConcernResponseDTO updateConcern(String id, ConcernRequestDTO dto) {
        Concern concern = concernRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Concern not found with ID: " + id));

        if (dto.studentId() != null) {
            Student student = studentRepository.findById(dto.studentId())
                    .orElseThrow(() -> new RuntimeException("Student not found with ID: " + dto.studentId()));
            concern.setStudent(student);
        }
        concern.setDescription(dto.description());
        concern.setStatus(dto.status());

        Concern updatedConcern = concernRepository.save(concern);

        kafkaTemplate.send("school-events-topic", "Concern updated: " + updatedConcern.getId());

        return mapToResponse(updatedConcern);
    }

    @Transactional
    public void deleteConcern(String id) {
        Concern concern = concernRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Concern not found with ID: " + id));
        concernRepository.delete(concern);

        kafkaTemplate.send("school-events-topic", "Concern deleted: " + id);
    }

    private ConcernResponseDTO mapToResponse(Concern concern) {
        return new ConcernResponseDTO(
                concern.getId(),
                concern.getDescription(),
                concern.getSubmittedAt(),
                concern.getStatus(),
                new ConcernResponseDTO.StudentSummaryDTO(concern.getStudent().getId(), concern.getStudent().getName())
        );
    }
}
