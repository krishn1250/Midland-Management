package com.school.midland.userservice.service.concerns;

import com.school.midland.commonlib.dtos.ConcernDto;
import com.school.midland.userservice.models.Concern;

import java.util.List;

public interface ConcernService {
    Concern createConcern(ConcernDto dto);
    List<Concern> getAllConcerns();
    Concern updateConcern(Long id, ConcernDto dto);
    void deleteConcern(Long id);
}
