package com.school.midland.userservice.repository;

import com.school.midland.userservice.models.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportRepository extends JpaRepository<Transport, Long> {
}