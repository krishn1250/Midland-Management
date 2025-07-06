package com.studentmanagement.test02.repo;

import com.studentmanagement.test02.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// TeacherRepository.java
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {

    Optional<Teacher> findByEmail(String email);

    List<Teacher> findBySubject(String subject);

    @Query("SELECT t FROM Teacher t JOIN FETCH t.timetables WHERE t.tuuid = :tuuid")
    Optional<Teacher> findByIdWithTimetables(@Param("tuuid") String tuuid);
}