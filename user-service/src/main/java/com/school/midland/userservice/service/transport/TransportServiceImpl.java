package com.school.midland.userservice.service.transport;

import com.school.midland.commonlib.dtos.TransportDto;
import com.school.midland.userservice.models.Transport;
import com.school.midland.userservice.repository.TransportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransportServiceImpl implements TransportService {
    private final TransportRepository repo;

    public Transport createTransport(TransportDto dto) {
        Transport t = Transport.builder()
                .admissionNumber(dto.getAdmissionNumber())
                .pickupLocation(dto.getPickupLocation())
                .dropLocation(dto.getDropLocation())
                .routeNumber(dto.getRouteNumber())
                .vehicleNumber(dto.getVehicleNumber())
                .driverName(dto.getDriverName())
                .driverContact(dto.getDriverContact())
                .isActive(dto.getIsActive())
                .build();
        return repo.save(t);
    }
    public List<Transport> getAllTransports() { return repo.findAll(); }
    public Transport updateTransport(Long id, TransportDto dto) {
        return repo.findById(id).map(t -> {
            t.setPickupLocation(dto.getPickupLocation());
            t.setDropLocation(dto.getDropLocation());
            t.setIsActive(dto.getIsActive());
            return repo.save(t);
        }).orElseThrow(() -> new RuntimeException("Transport not found"));
    }
    public void deleteTransport(Long id) { repo.deleteById(id); }
}
