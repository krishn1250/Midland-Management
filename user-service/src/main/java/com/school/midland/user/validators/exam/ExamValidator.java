package com.school.midland.user.validators.exam;

import com.school.midland.user.models.Exams;
import com.school.midland.user.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.school.midland.user.exception.UserException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExamValidator {

    private final ExamRepository examRepository;

    public void validateForCreate(Exams exam) {
        validateExamData(exam);
    }

    public void validateForUpdate(Exams updated, Exams existing) {
        if (!existing.getIsActive()) {
            throw new UserException("Cannot update an inactive (deleted) exam.", HttpStatus.BAD_REQUEST);
        }
        validateExamData(updated);
    }

    private void validateExamData(Exams exam) {
        if (exam.getStartTime() == null || exam.getEndTime() == null)
            throw new UserException("Exam start and end time must be provided", HttpStatus.BAD_REQUEST);

        if (exam.getStartTime().isAfter(exam.getEndTime()))
            throw new UserException("Exam start time cannot be after end time", HttpStatus.BAD_REQUEST);

        if (exam.getGradeLevel() == null || exam.getGradeLevel().isEmpty())
            throw new UserException("Grade level must be provided", HttpStatus.BAD_REQUEST);

        List<Exams> overlapping = examRepository.findActiveExamsByGradeLevel(
                exam.getGradeLevel(), exam.getStartTime(), exam.getEndTime());

        boolean hasConflict = overlapping.stream()
                .anyMatch(e -> !e.getExamId().equals(exam.getExamId()));

        if (hasConflict)
            throw new UserException("Overlapping exam schedule found for grade: " + exam.getGradeLevel(), HttpStatus.CONFLICT);
    }
}
