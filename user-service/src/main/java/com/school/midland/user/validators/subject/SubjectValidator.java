package com.school.midland.user.validators.subject;

import com.school.midland.user.dto.SubjectDto;
import com.school.midland.user.exception.UserException;
import com.school.midland.user.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class SubjectValidator {

    private final SubjectRepository subjectRepository;

    public void validateForCreate(SubjectDto dto) {
        validateBasic(dto);
        if (subjectRepository.existsBySubjectCode(dto.getSubjectCode())) {
            throw new UserException("Subject code already exists: " + dto.getSubjectCode(), HttpStatus.CONFLICT);
        }
    }

    public void validateBasic(SubjectDto dto) {
        if (dto == null)
            throw new UserException("Subject details are required", HttpStatus.BAD_REQUEST);

        if (!StringUtils.hasText(dto.getSubjectCode()))
            throw new UserException("Subject code is mandatory", HttpStatus.BAD_REQUEST);

        if (!StringUtils.hasText(dto.getSubjectName()))
            throw new UserException("Subject name is mandatory", HttpStatus.BAD_REQUEST);

        if (!StringUtils.hasText(dto.getGradeLevel()))
            throw new UserException("Grade level is mandatory", HttpStatus.BAD_REQUEST);

        if (!StringUtils.hasText(dto.getTeacherCode()))
            throw new UserException("Teacher code is mandatory", HttpStatus.BAD_REQUEST);

        if (!StringUtils.hasText(dto.getSchoolCode()))
            throw new UserException("School code is mandatory", HttpStatus.BAD_REQUEST);
    }
}
