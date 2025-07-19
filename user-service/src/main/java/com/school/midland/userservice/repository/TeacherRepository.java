package com.school.midland.userservice.repository;

import com.school.midland.userservice.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    Optional<Teacher> findByUsername(String username);
    Optional<Teacher> findByTeacherUid(UUID teacherUid);
    Optional<Teacher> findByTeacherCode(String teacherCode);
    Optional<Teacher> findBySchoolEmail(String schoolEmail);
    Optional<List<Teacher>>  findByDepartment(String department);
    Optional<List<Teacher>>  findByDesignation(String department);


}
