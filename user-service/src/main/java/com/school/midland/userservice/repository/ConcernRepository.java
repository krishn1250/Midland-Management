package com.school.midland.userservice.repository;


import com.school.midland.userservice.models.Concern;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcernRepository extends JpaRepository<Concern, Long> {
}