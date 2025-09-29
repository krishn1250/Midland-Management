package com.school.midland.userservice.repository;



import com.school.midland.userservice.models.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Long> {
    List<Marks> findByAdmissionNumberAndExamId(String admissionNumber, Long examId);
    List<Marks> findByExamId(Long examId);
}