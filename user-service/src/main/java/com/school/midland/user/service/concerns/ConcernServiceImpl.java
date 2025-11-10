package com.school.midland.user.service.concerns;

import com.school.midland.commonlib.dtos.ConcernDto;
import com.school.midland.userservice.models.Concern;
import com.school.midland.userservice.repository.ConcernRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcernServiceImpl implements ConcernService {

    private final ConcernRepository concernRepository;

    @Override
    public Concern createConcern(ConcernDto dto) {
        Concern concern = Concern.builder()
                .raisedByRole(dto.getRaisedByRole())
                .raisedByIdentifier(dto.getRaisedByIdentifier())
                .admissionNumber(dto.getAdmissionNumber())
                .concernType(dto.getConcernType())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .assignedToTeacherCode(dto.getAssignedToTeacherCode())
                .response(dto.getResponse())
                .build();
        return concernRepository.save(concern);
    }

    @Override
    public List<Concern> getAllConcerns() {
        return concernRepository.findAll();
    }

    @Override
    public Concern updateConcern(Long id, ConcernDto dto) {
        return concernRepository.findById(id).map(concern -> {
            concern.setStatus(dto.getStatus());
            concern.setResponse(dto.getResponse());
            return concernRepository.save(concern);
        }).orElseThrow(() -> new RuntimeException("Concern not found"));
    }

    @Override
    public void deleteConcern(Long id) {
        concernRepository.deleteById(id);
    }
}
