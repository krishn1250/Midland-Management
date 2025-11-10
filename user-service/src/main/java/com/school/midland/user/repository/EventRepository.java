package com.school.midland.user.repository;


import com.school.midland.user.models.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Events, Long> {
}
