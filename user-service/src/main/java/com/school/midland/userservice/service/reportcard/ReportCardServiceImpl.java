package com.school.midland.userservice.service.reportcard;

import com.school.midland.userservice.models.Marks;
import com.school.midland.userservice.models.ReportCard;
import com.school.midland.userservice.repository.MarksRepository;
import com.school.midland.userservice.repository.ReportCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportCardServiceImpl implements ReportCardService {
    private final MarksRepository markRepo;
    private final ReportCardRepository repo;

    public ReportCard generateReportCard(String admissionNumber, Long examId) {
        List<Marks> marks = markRepo.findByAdmissionNumberAndExamId(admissionNumber, examId);
        if (marks.isEmpty()) throw new RuntimeException("No marks found");

        int total = marks.stream().mapToInt(Marks::getObtainedMarks).sum();
        int max = marks.stream().mapToInt(Marks::getMaxMarks).sum();
        String grade = (total * 100 / max) >= 90 ? "A" : (total * 100 / max >= 75 ? "B" : "C");

        ReportCard rc = ReportCard.builder()
                .admissionNumber(admissionNumber)
                .examId(examId)
                .totalMarks(total)
                .maxMarks(max)
                .overallGrade(grade)
                .remarks("Auto-generated")
                .approvedByTeacherCode("SYSTEM")
                .build();
        return repo.save(rc);
    }

    public List<ReportCard> getAllReportCards() { return repo.findAll(); }
}