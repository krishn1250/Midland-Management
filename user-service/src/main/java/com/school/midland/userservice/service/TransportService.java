// TransportService.java (in user-service/src/main/java/com/school/midland/userservice/service)
package com.school.midland.userservice.service;

import com.school.midland.userservice.dto.TransportRouteRequestDTO;
import com.school.midland.userservice.dto.TransportRouteResponseDTO;
import com.school.midland.userservice.model.Student;
import com.school.midland.userservice.model.TransportRoute;
import com.school.midland.userservice.repository.StudentRepository;
import com.school.midland.userservice.repository.TransportRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransportService {
    private final TransportRouteRepository transportRouteRepository;
    private final StudentRepository studentRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public TransportRouteResponseDTO createTransportRoute(TransportRouteRequestDTO dto) {
        TransportRoute route = new TransportRoute();
        route.setId(UUID.randomUUID().toString());
        route.setRouteName(dto.routeName());
        route.setDriverName(dto.driverName());
        route.setVehicleNumber(dto.vehicleNumber());

        if (dto.studentIds() != null) {
            List<Student> students = studentRepository.findAllById(dto.studentIds());
            route.setStudents(students);
        }

        TransportRoute savedRoute = transportRouteRepository.save(route);

        kafkaTemplate.send("school-events-topic", "New transport route created: " + savedRoute.getRouteName());

        return mapToResponse(savedRoute);
    }

    public List<TransportRouteResponseDTO> getAllTransportRoutes() {
        return transportRouteRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public TransportRouteResponseDTO getTransportRouteById(String id) {
        TransportRoute route = transportRouteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transport route not found with ID: " + id));
        return mapToResponse(route);
    }

    @Transactional
    public TransportRouteResponseDTO updateTransportRoute(String id, TransportRouteRequestDTO dto) {
        TransportRoute route = transportRouteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transport route not found with ID: " + id));

        route.setRouteName(dto.routeName());
        route.setDriverName(dto.driverName());
        route.setVehicleNumber(dto.vehicleNumber());

        if (dto.studentIds() != null) {
            List<Student> students = studentRepository.findAllById(dto.studentIds());
            route.setStudents(students);
        }

        TransportRoute updatedRoute = transportRouteRepository.save(route);

        kafkaTemplate.send("school-events-topic", "Transport route updated: " + updatedRoute.getRouteName());

        return mapToResponse(updatedRoute);
    }

    @Transactional
    public void deleteTransportRoute(String id) {
        TransportRoute route = transportRouteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transport route not found with ID: " + id));
        transportRouteRepository.delete(route);

        kafkaTemplate.send("school-events-topic", "Transport route deleted: " + route.getRouteName());
    }

    private TransportRouteResponseDTO mapToResponse(TransportRoute route) {
        return new TransportRouteResponseDTO(
                route.getId(),
                route.getRouteName(),
                route.getDriverName(),
                route.getVehicleNumber(),
                route.getStudents().stream()
                        .map(student -> new TransportRouteResponseDTO.StudentSummaryDTO(student.getId(), student.getName()))
                        .toList()
        );
    }
}
