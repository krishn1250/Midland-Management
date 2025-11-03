package com.school.midland.auth.repository;


import com.school.midland.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndRole(String username, String role);
    Optional<User> findByEmailAndRole(String email, String role);
    Optional<User> findByEmail(String email);
    Optional<User> findByUserUid(UUID userUid);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}