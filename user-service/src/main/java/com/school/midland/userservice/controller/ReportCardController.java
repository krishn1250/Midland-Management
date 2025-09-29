package com.school.midland.userservice.controller;

import com.school.midland.userservice.models.ReportCard;
import com.school.midland.userservice.service.reportcard.ReportCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report-cards")
@RequiredArgsConstructor
public class ReportCardController {
    private final ReportCardService service;

    @PostMapping("/generate/{admissionNumber}/{examId}")
    public ReportCard generate(@PathVariable String admissionNumber, @PathVariable Long examId) {
        return service.generateReportCard(admissionNumber, examId);
    }

    @GetMapping
    public List<ReportCard> getAll() { return service.getAllReportCards(); }
}
