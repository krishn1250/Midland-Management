package com.school.midland.user.controller;


import com.school.midland.user.dto.TransportDto;
import com.school.midland.user.models.Transport;
import com.sun.jdi.connect.spi.TransportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transports")
@RequiredArgsConstructor
public class TransportController {
    private final TransportService service;

//    @PostMapping
//    public Transport create(@RequestBody TransportDto dto) { return service.createTransport(dto); }
//    @GetMapping
//    public List<Transport> getAll() { return service.getAllTransports(); }
//    @PutMapping("/{id}") public Transport update(@PathVariable Long id, @RequestBody TransportDto dto) { return service.updateTransport(id, dto); }
//    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.deleteTransport(id); }
}
