package com.school.midland.user.service.marks;

import com.school.midland.user.dto.MarksDto;
import com.school.midland.user.exception.UserException;
import com.school.midland.user.mappers.MarksMapper;
import com.school.midland.user.models.Marks;
import com.school.midland.user.models.Student;
import com.school.midland.user.models.Exams;
import com.school.midland.user.repository.MarksRepository;
import com.school.midland.user.repository.StudentRepository;
import com.school.midland.user.repository.ExamRepository;
import com.school.midland.user.validators.marks.MarksValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarksServiceImpl implements MarksService {

    private final MarksRepository marksRepository;
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;
    private final MarksValidator marksValidator;

    @Override
    @Transactional
    public MarksDto addMarks(MarksDto dto) {
        // Validate basic input
        marksValidator.validateMarksInput(dto);

        // Validate student exists
        Student student = studentRepository.findByAdmissionNumber(dto.getAdmissionNumber())
                .orElseThrow(() -> new UserException(
                        "Student not found: " + dto.getAdmissionNumber(), HttpStatus.NOT_FOUND));

        // Validate exam exists and is active
        Exams exam = examRepository.findById(dto.getExamId())
                .filter(Exams::getIsActive)
                .orElseThrow(() -> new UserException(
                        "Cannot record marks for non-existent or inactive exam with ID: " + dto.getExamId(),
                        HttpStatus.BAD_REQUEST));

        // Check duplicate marks for same student, exam, subject
        boolean exists = marksRepository.findByAdmissionNumberAndExamId(dto.getAdmissionNumber(), dto.getExamId())
                .stream()
                .anyMatch(m -> m.getSubjectCode().equalsIgnoreCase(dto.getSubjectCode()));
        if (exists)
            throw new UserException("Marks already recorded for this student, exam, and subject", HttpStatus.CONFLICT);

        // Compute grade
        dto.setGrade(marksValidator.computeGrade(dto.getObtainedMarks(), dto.getMaxMarks()));

        // Save and return
        Marks saved = marksRepository.save(MarksMapper.toEntity(dto));
        return MarksMapper.toDTO(saved);
    }

    @Override
    @Transactional
    public MarksDto updateMarks(Long id, MarksDto dto) {
        Marks marks = marksRepository.findById(id)
                .orElseThrow(() -> new UserException("Marks not found: " + id, HttpStatus.NOT_FOUND));

        // Validate update input
        marksValidator.validateUpdateInput(dto, marks);

        // Optional updates
        Optional.ofNullable(dto.getMaxMarks()).ifPresent(marks::setMaxMarks);
        Optional.ofNullable(dto.getObtainedMarks()).ifPresent(marks::setObtainedMarks);
        Optional.ofNullable(dto.getRemarks()).ifPresent(marks::setRemarks);
        Optional.ofNullable(dto.getRecordedByTeacherCode()).ifPresent(marks::setRecordedByTeacherCode);

        // Recompute grade
        marks.setGrade(marksValidator.computeGrade(marks.getObtainedMarks(), marks.getMaxMarks()));

        return MarksMapper.toDTO(marksRepository.save(marks));
    }

    @Override
    public void deleteMarks(Long id) {
        if (!marksRepository.existsById(id)) {
            throw new UserException("Marks not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        marksRepository.deleteById(id);
    }

    @Override
    public MarksDto getMarksById(Long id) {
        return marksRepository.findById(id)
                .map(MarksMapper::toDTO)
                .orElseThrow(() -> new UserException("Marks not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public List<MarksDto> getStudentMarks(String admissionNumber, Long examId) {
        return marksRepository.findByAdmissionNumberAndExamId(admissionNumber, examId)
                .stream().map(MarksMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<MarksDto> getExamMarks(Long examId) {
        return marksRepository.findByExamId(examId)
                .stream().map(MarksMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<MarksDto> getAllMarks() {
        return marksRepository.findAll()
                .stream().map(MarksMapper::toDTO).collect(Collectors.toList());
    }
}
