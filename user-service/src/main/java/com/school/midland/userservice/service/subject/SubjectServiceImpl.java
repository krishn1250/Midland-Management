package com.school.midland.userservice.service.subject;

import com.school.midland.commonlib.dtos.SubjectDto;
import com.school.midland.commonlib.exception.UserException;
import com.school.midland.userservice.mappers.SubjectMapper;
import com.school.midland.userservice.models.Subject;
import com.school.midland.userservice.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.UnknownSqlResultSetMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements  SubjectService{


    private final SubjectRepository subjectRepository;

    @Override
    public SubjectDto createSubject(SubjectDto dto) {
        if (dto == null)
            throw new UserException("Enter mandatory fields", HttpStatus.BAD_REQUEST);

        Optional<Subject> exists = subjectRepository.findBySubjectCode(dto.getSubjectCode());
        if (exists.isPresent())
            throw new UserException("Subject code already exists", HttpStatus.BAD_REQUEST);

        Subject entity = SubjectMapper.toEntity(dto);
        Subject saved = subjectRepository.save(entity);

        if (saved == null)
            throw new UserException("Something went wrong while saving the subject", HttpStatus.BAD_REQUEST);

        return SubjectMapper.toDto(saved);
    }

    @Override
    public List<SubjectDto> createSubjects(List<SubjectDto> subjectDto) {
        if(subjectDto.isEmpty()) throw new UserException("no data inserted", HttpStatus.BAD_REQUEST);
            List<Subject> filtered=subjectDto.stream().filter(dto->!subjectRepository.existsBySubjectCode(dto.getSubjectCode()))
                    .map(SubjectMapper::toEntity).collect(Collectors.toUnmodifiableList());

        List<Subject> subjects = subjectRepository.saveAll(filtered);

        if(subjects.isEmpty()) throw new UserException("unable to save data",HttpStatus.BAD_REQUEST);

        return SubjectMapper.toDtoList(subjects);
    }

    @Override
    public SubjectDto updateSubject(String subjectCode, SubjectDto dto) {
        Optional<Subject> existingOpt = subjectRepository.findBySubjectCode(subjectCode);

        if (existingOpt.isEmpty())
            throw new UserException("Subject not found with code: " + subjectCode, HttpStatus.NOT_FOUND);

        Subject existing = existingOpt.get();
        existing.setSubjectName(dto.getSubjectName());
        existing.setCurriculumType(dto.getCurriculumType());
        existing.setGradeLevel(dto.getGradeLevel());
        existing.setTeacherCode(dto.getTeacherCode());

        Subject updated = subjectRepository.save(existing);
        return SubjectMapper.toDto(updated);
    }

    @Override
    public boolean deleteSubject(String code) {
        Optional<Subject> subjectOpt = subjectRepository.findBySubjectCode(code);
        if (subjectOpt.isEmpty())
            throw new UserException("Subject not found with code: " + code, HttpStatus.NOT_FOUND);

        subjectRepository.delete(subjectOpt.get());
        return true;
    }

    @Override
    public List<SubjectDto> getAllSubjects() {
        return subjectRepository.findAll().stream().map(SubjectMapper::toDto).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<SubjectDto> getSubjectsByGrade(String gradeLevel) {
        if (gradeLevel == null || gradeLevel.isEmpty())
            throw new UserException("Grade level is required", HttpStatus.BAD_REQUEST);

        List<Subject> subjects = subjectRepository.findByGradeLevel(gradeLevel);
        List<SubjectDto> dtos = new ArrayList<>();
        for (Subject s : subjects) {
            dtos.add(SubjectMapper.toDto(s));
        }
        return dtos;
    }

    @Override
    public List<SubjectDto> getSubjectsByTeacher(String teacherCode) {
        if (teacherCode == null || teacherCode.isEmpty())
            throw new UserException("Teacher code is required", HttpStatus.BAD_REQUEST);

        List<Subject> subjects = subjectRepository.findByTeacherCode(teacherCode);
        List<SubjectDto> dtos = new ArrayList<>();
        for (Subject s : subjects) {
            dtos.add(SubjectMapper.toDto(s));
        }
        return dtos;
    }

    @Override
    public SubjectDto getSubjectByCode(String code) {
        if (code == null || code.isEmpty())
            throw new UserException("Subject code is required", HttpStatus.BAD_REQUEST);

        Optional<Subject> subject = subjectRepository.findBySubjectCode(code);
        if (subject.isEmpty())
            throw new UserException("Subject not found with code: " + code, HttpStatus.NOT_FOUND);

        return SubjectMapper.toDto(subject.get());
    }
}
