package com.school.midland.userservice.service.marks;

import com.school.midland.commonlib.dtos.MarksDto;
import com.school.midland.commonlib.exception.UserException;
import com.school.midland.userservice.mappers.MarksMapper;
import com.school.midland.userservice.models.Marks;
import com.school.midland.userservice.models.Student;
import com.school.midland.userservice.repository.MarksRepository;
import com.school.midland.userservice.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarksServiceImpl implements MarksService {

    private final MarksRepository marksRepository;
    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public MarksDto addMarks(MarksDto dto) {
        // validate student exists
        Student student = studentRepository.findByAdmissionNumber(dto.getAdmissionNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with admissionNumber: " + dto.getAdmissionNumber()));

        // validate marks
        if (dto.getObtainedMarks() != null && dto.getMaxMarks() != null && dto.getObtainedMarks() > dto.getMaxMarks()) {
            throw new UserException("validation error", HttpStatus.BAD_REQUEST);
        }

        // compute grade
        String grade = computeGrade(dto.getObtainedMarks(), dto.getMaxMarks());
        dto.setGrade(grade);

        Marks entity = MarksMapper.toEntity(dto);
        Marks saved = marksRepository.save(entity);
        return MarksMapper.toDTO(saved);
    }

    @Override
    @Transactional
    public MarksDto updateMarks(Long id, MarksDto dto) {
        Marks marks = marksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marks not found with id: " + id));

        if (dto.getAdmissionNumber() != null && !dto.getAdmissionNumber().equals(marks.getAdmissionNumber())) {
            // ensure new admissionNumber exists
            studentRepository.findByAdmissionNumber(dto.getAdmissionNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Student not found with admissionNumber: " + dto.getAdmissionNumber()));
            marks.setAdmissionNumber(dto.getAdmissionNumber());
        }

        if (dto.getMaxMarks() != null) marks.setMaxMarks(dto.getMaxMarks());
        if (dto.getObtainedMarks() != null) marks.setObtainedMarks(dto.getObtainedMarks());
        if (dto.getSubjectCode() != null) marks.setSubjectCode(dto.getSubjectCode());
        if (dto.getSubjectName() != null) marks.setSubjectName(dto.getSubjectName());
        if (dto.getExamId() != null) marks.setExamId(dto.getExamId());
        if (dto.getRemarks() != null) marks.setRemarks(dto.getRemarks());
        if (dto.getRecordedByTeacherCode() != null) marks.setRecordedByTeacherCode(dto.getRecordedByTeacherCode());

        // validate
        if (marks.getObtainedMarks() != null && marks.getMaxMarks() != null && marks.getObtainedMarks() > marks.getMaxMarks()) {
            throw new UserException("validation error",HttpStatus.BAD_REQUEST);
        }

        // recompute grade
        marks.setGrade(computeGrade(marks.getObtainedMarks(), marks.getMaxMarks()));

        Marks updated = marksRepository.save(marks);
        return MarksMapper.toDTO(updated);
    }

    @Override
    public void deleteMarks(Long id) {
        if (!marksRepository.existsById(id)) {
            throw new ResourceNotFoundException("Marks not found with id: " + id);
        }
        marksRepository.deleteById(id);
    }

    @Override
    public MarksDto getMarksById(Long id) {
        return marksRepository.findById(id)
                .map(MarksMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Marks not found with id: " + id));
    }

    @Override
    public List<MarksDto> getStudentMarks(String admissionNumber, Long examId) {
        return marksRepository.findByAdmissionNumberAndExamId(admissionNumber, examId)
                .stream()
                .map(MarksMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MarksDto> getExamMarks(Long examId) {
        return marksRepository.findByExamId(examId)
                .stream()
                .map(MarksMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MarksDto> getAllMarks() {
        return marksRepository.findAll()
                .stream()
                .map(MarksMapper::toDTO)
                .collect(Collectors.toList());
    }

    // grade computation logic
    private String computeGrade(Integer obtained, Integer max) {
        if (obtained == null || max == null || max == 0) return null;
        double perc = (obtained * 100.0) / max;
        if (perc >= 90) return "A";
        if (perc >= 75) return "B";
        if (perc >= 60) return "C";
        if (perc >= 40) return "D";
        return "F";
    }
}