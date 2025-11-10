package com.school.midland.user.validators.marks;




import com.school.midland.user.dto.MarksDto;
import com.school.midland.user.exception.UserException;
import com.school.midland.user.models.Marks;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class MarksValidator {

    public void validateMarksInput(MarksDto dto) {
        if (dto.getAdmissionNumber() == null || dto.getAdmissionNumber().isBlank())
            throw new UserException("Admission number is required", HttpStatus.BAD_REQUEST);
        if (dto.getSubjectCode() == null || dto.getSubjectCode().isBlank())
            throw new UserException("Subject code is required", HttpStatus.BAD_REQUEST);
        if (dto.getExamId() == null)
            throw new UserException("Exam ID is required", HttpStatus.BAD_REQUEST);
        if (dto.getMaxMarks() == null || dto.getMaxMarks() <= 0)
            throw new UserException("Max marks must be > 0", HttpStatus.BAD_REQUEST);
        if (dto.getObtainedMarks() == null || dto.getObtainedMarks() < 0)
            throw new UserException("Obtained marks must be ≥ 0", HttpStatus.BAD_REQUEST);
        if (dto.getObtainedMarks() > dto.getMaxMarks())
            throw new UserException("Obtained marks cannot exceed max marks", HttpStatus.BAD_REQUEST);
    }

    public void validateUpdateInput(MarksDto dto, Marks existing) {
        if (dto.getObtainedMarks() != null && dto.getMaxMarks() != null && dto.getObtainedMarks() > dto.getMaxMarks())
            throw new UserException("Obtained marks cannot exceed max marks", HttpStatus.BAD_REQUEST);
    }

    public String computeGrade(Integer obtained, Integer max) {
        if (obtained == null || max == null || max == 0) return null;
        double perc = (obtained * 100.0) / max;
        if (perc >= 90) return "A+";
        if (perc >= 80) return "A";
        if (perc >= 70) return "B+";
        if (perc >= 60) return "B";
        if (perc >= 50) return "C";
        if (perc >= 40) return "D";
        return "F";
    }
}
