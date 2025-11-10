package com.school.midland.user.controller;

import com.school.midland.commonlib.dtos.ConcernDto;
import com.school.midland.userservice.models.Concern;
import com.school.midland.userservice.service.concerns.ConcernService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/concerns")
@RequiredArgsConstructor
public class ConcernsController {
    private final ConcernService concernService;

    @PostMapping
    public Concern create(@RequestBody ConcernDto dto) {
        return concernService.createConcern(dto);
    }

    @GetMapping
    public List<Concern> getAll() {
        return concernService.getAllConcerns();
    }

    @PutMapping("/{id}")
    public Concern update(@PathVariable Long id, @RequestBody ConcernDto dto) {
        return concernService.updateConcern(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        concernService.deleteConcern(id);
    }
}