package com.school.midland.userservice.controller;

import com.school.midland.userservice.dto.TransportRouteRequestDTO;
import com.school.midland.userservice.dto.TransportRouteResponseDTO;
import com.school.midland.userservice.service.TransportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transport-routes")
@RequiredArgsConstructor
public class TransportController {
    private final TransportService transportService;

    @PostMapping
    public ResponseEntity<TransportRouteResponseDTO> createTransportRoute(@Valid @RequestBody TransportRouteRequestDTO request) {
        TransportRouteResponseDTO created = transportService.createTransportRoute(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TransportRouteResponseDTO>> getAllTransportRoutes() {
        List<TransportRouteResponseDTO> routes = transportService.getAllTransportRoutes();
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransportRouteResponseDTO> getTransportRouteById(@PathVariable String id) {
        TransportRouteResponseDTO route = transportService.getTransportRouteById(id);
        return ResponseEntity.ok(route);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransportRouteResponseDTO> updateTransportRoute(@PathVariable String id, @Valid @RequestBody TransportRouteRequestDTO request) {
        TransportRouteResponseDTO updated = transportService.updateTransportRoute(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransportRoute(@PathVariable String id) {
        transportService.deleteTransportRoute(id);
        return ResponseEntity.noContent().build();
    }
}
