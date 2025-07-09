// --- File: repository/AttendanceRepository.java ---
package com.school.midland.userservice.repository;

import com.school.midland.userservice.model.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceRecord, String> {
}