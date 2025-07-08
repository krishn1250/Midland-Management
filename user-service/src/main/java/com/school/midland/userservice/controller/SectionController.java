package com.school.midland.userservice.controller;

import com.school.midland.userservice.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sections")
@RequiredArgsConstructor
public class SectionController {
    private final SectionService sectionService;

    @GetMapping
    public String getSections() {
        return "List of sections"; // Placeholder for actual implementation
    }
}
