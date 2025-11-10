package com.school.midland.user.validators.syllabus;



import com.school.midland.user.exception.UserException;
import com.school.midland.user.models.Syllabus;
import com.school.midland.user.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SyllabusValidator {

    private final SubjectRepository subjectRepository;

    /**
     * Validate before adding or updating syllabus
     */
    public void validateBeforeSave(Syllabus syllabus) {
        if (syllabus.getSubjectCode() == null || syllabus.getSubjectCode().isBlank()) {
            throw new UserException("Subject code cannot be empty", HttpStatus.BAD_REQUEST);
        }

        if (syllabus.getGradeLevel() == null || syllabus.getGradeLevel().isBlank()) {
            throw new UserException("Grade level cannot be empty", HttpStatus.BAD_REQUEST);
        }

        // ✅ Validate subject exists
        boolean subjectExists = subjectRepository
                .existsBySubjectCodeAndGradeLevel(syllabus.getSubjectCode(), syllabus.getGradeLevel());

        if (!subjectExists) {
            throw new UserException("Invalid subject code or grade level — subject not found", HttpStatus.NOT_FOUND);
        }

        if (syllabus.getTopicTitle() == null || syllabus.getTopicTitle().isBlank()) {
            throw new UserException("Topic title cannot be empty", HttpStatus.BAD_REQUEST);
        }
    }
}

