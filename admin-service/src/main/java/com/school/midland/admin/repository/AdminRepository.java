package com.school.midland.admin.repo;

import com.school.midland.admin.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByAdminUid(UUID adminUid);
    Optional<Admin> findByUserUid(UUID userUid);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<Admin> findByUsername(String username);
    Optional<Admin>  findByEmail(String email);
    boolean existsByUsername(String admin);
}
