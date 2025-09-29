package com.school.midland.userservice.repository;

import com.school.midland.userservice.models.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Events, Long> {
}
