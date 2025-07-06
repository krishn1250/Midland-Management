package com.studentmanagement.test02.repo;

import com.studentmanagement.test02.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
// StudentRepository.java
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    List<Student> findBySectionSectionId(Long sectionId);

    Optional<Student> findByRollNumber(String rollNumber);

    @Query("SELECT s FROM Student s JOIN FETCH s.section WHERE s.suuid = :suuid")
    Optional<Student> findByIdWithSection(@Param("suuid") String suuid);
}