package com.school.midland.authservice.repository;



import com.school.midland.authservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndRole(String username,String role);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameAndPassword(String username,String password);
    Optional<User> findByUserUid(UUID userUid);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}