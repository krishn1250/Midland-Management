package com.school.midland.user.repository;


import com.school.midland.user.models.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportRepository extends JpaRepository<Transport, Long> {
}