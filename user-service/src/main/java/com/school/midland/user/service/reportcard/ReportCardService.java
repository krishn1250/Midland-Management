package com.school.midland.user.service.reportcard;

import com.school.midland.userservice.models.ReportCard;

import java.util.List;

public interface ReportCardService {
    ReportCard generateReportCard(String admissionNumber, Long examId);
    List<ReportCard> getAllReportCards();
}