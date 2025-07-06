package com.school.midland.adminservice.repository;

import com.school.midland.adminservice.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByAdminUid(UUID adminUid);
    Optional<Admin> findByUserUid(UUID userUid);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);



    Optional<Admin> findByUsername(String username);

    boolean existsByUsername(String admin);
}