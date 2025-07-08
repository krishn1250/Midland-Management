// --- File: repository/StudentRepository.java ---
package com.school.midland.userservice.repository;

import com.school.midland.userservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    // Spring Data JPA gives you findAll(), findById(), save(), delete() etc.
    // You can add custom queries here later, like findByEmail(String email).
}