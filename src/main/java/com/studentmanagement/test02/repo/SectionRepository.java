package com.studentmanagement.test02.repo;

import com.studentmanagement.test02.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// SectionRepository.java
@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

    List<Section> findByGradeAndAcademicYear(String grade, String academicYear);

    List<Section> findByAcademicYear(String academicYear);

    Optional<Section> findByGradeAndSectionNameAndAcademicYear(String grade, String sectionName, String academicYear);

    @Query("SELECT s FROM Section s JOIN FETCH s.students WHERE s.sectionId = :sectionId")
    Optional<Section> findByIdWithStudents(@Param("sectionId") Long sectionId);
}