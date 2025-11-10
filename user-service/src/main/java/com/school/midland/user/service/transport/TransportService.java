package com.school.midland.user.service.transport;

import com.school.midland.commonlib.dtos.TransportDto;
import com.school.midland.userservice.models.Transport;

import java.util.List;

public interface TransportService {
    Transport createTransport(TransportDto dto);
    List<Transport> getAllTransports();
    Transport updateTransport(Long id, TransportDto dto);
    void deleteTransport(Long id);
}
