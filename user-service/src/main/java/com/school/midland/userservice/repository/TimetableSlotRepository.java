package com.school.midland.userservice.repository;

import com.school.midland.userservice.model.TimetableSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableSlotRepository extends JpaRepository<TimetableSlot, String> {
}
