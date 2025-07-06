package com.studentmanagement.test02.repo;

import com.studentmanagement.test02.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// SubjectRepository.java
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional<Subject> findBySubjectCode(String subjectCode);

    Optional<Subject> findBySubjectName(String subjectName);

    List<Subject> findBySubjectNameContainingIgnoreCase(String keyword);
}