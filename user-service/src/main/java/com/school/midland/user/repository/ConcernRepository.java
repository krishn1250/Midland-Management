package com.school.midland.user.repository;



import com.school.midland.user.models.Concern;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcernRepository extends JpaRepository<Concern, Long> {
}