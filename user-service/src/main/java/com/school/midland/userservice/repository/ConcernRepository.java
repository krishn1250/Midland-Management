package com.school.midland.userservice.repository;

import com.school.midland.userservice.model.Concern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcernRepository extends JpaRepository<Concern, String> {}
