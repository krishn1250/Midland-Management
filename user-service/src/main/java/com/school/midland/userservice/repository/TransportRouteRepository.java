package com.school.midland.userservice.repository;

import com.school.midland.userservice.model.TransportRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRouteRepository extends JpaRepository<TransportRoute, String> {}