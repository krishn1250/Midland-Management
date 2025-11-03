package com.school.midland.admin.mapper;

import com.school.midland.admin.dtos.admin.AdminDto;
import com.school.midland.admin.dtos.admin.AdminResponseDto;
import com.school.midland.admin.models.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AdminMapper {

    private final PasswordEncoder passwordEncoder;

    /**
     * Maps AdminDto -> Admin Entity (for creation)
     */
    public Admin dtoToEntity(AdminDto dto) {
        if (dto == null) return null;

        String[] parts = {"", ""};
        if (dto.getFullName() != null) {
            parts = dto.getFullName().split(" ", 2);
        }

        return Admin.builder()
                .adminUid(UUID.randomUUID())
                .userUid(dto.getUserUid()) // may be null during registration; filled later
                .username(dto.getUsername())
                .firstName(parts[0])
                .lastName(parts.length > 1 ? parts[1] : "")
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .designation(dto.getDesignation() != null ? dto.getDesignation() : "System Admin")
                .profileImage(dto.getProfileImage())
                .notes(dto.getNotes())
                .isActive(dto.getIsActive() != null ? dto.getIsActive() : true)
                .schoolCode(dto.getSchoolCode())
                .createdAt(LocalDateTime.now())
                .build();
    }

    /**
     * Maps Admin Entity -> AdminDto (for response)
     */
    public AdminDto entityToDto(Admin entity) {
        if (entity == null) return null;

        return AdminDto.builder()
                .adminId(entity.getAdminId())
                .adminUid(entity.getAdminUid())
                .userUid(entity.getUserUid())
                .username(entity.getUsername())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .fullName(entity.getFullName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .designation(entity.getDesignation())
                .profileImage(entity.getProfileImage())
                .notes(entity.getNotes())
                .isActive(entity.getIsActive())
                .schoolCode(entity.getSchoolCode())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())

                .build();
    }

    /**
     * Updates existing Admin entity with non-null fields from DTO.
     */
    public void updateEntityFromDto(AdminDto dto, Admin entity) {
        if (dto == null || entity == null) return;

        if (dto.getFullName() != null) {
            entity.setFullName(dto.getFullName());
            String[] parts = dto.getFullName().split(" ", 2);
            entity.setFirstName(parts[0]);
            entity.setLastName(parts.length > 1 ? parts[1] : "");
        }

        if (dto.getEmail() != null) entity.setEmail(dto.getEmail());
        if (dto.getPhoneNumber() != null) entity.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getDesignation() != null) entity.setDesignation(dto.getDesignation());
        if (dto.getProfileImage() != null) entity.setProfileImage(dto.getProfileImage());
        if (dto.getNotes() != null) entity.setNotes(dto.getNotes());
        if (dto.getIsActive() != null) entity.setIsActive(dto.getIsActive());
        if (dto.getSchoolCode() != null) entity.setSchoolCode(dto.getSchoolCode());

        // password is handled only in Auth service, not here
        entity.setUpdatedAt(LocalDateTime.now());
    }

        public AdminResponseDto toResponseDto(Admin admin) {
            if (admin == null) {
                return null; // Or throw an exception if appropriate
            }

            return AdminResponseDto.builder()
                    .id(Math.toIntExact(admin.getAdminId()))
                    .firstName(admin.getFirstName())
                    .lastName(admin.getLastName())
                    .username(admin.getUsername())
                    .fullName(admin.getFullName())
                    .email(admin.getEmail())
                    .phoneNumber(admin.getPhoneNumber())
                    .designation(admin.getDesignation())
                    .profileImage(admin.getProfileImage())
                    .notes(admin.getNotes())
                    .isActive(admin.getIsActive())
                    .schoolCode(admin.getSchoolCode())
                    .createdAt(admin.getCreatedAt())
                    .updatedAt(admin.getUpdatedAt())
                    .build();
        }

    /**
     * Encodes a raw password if present (used before passing to AuthServiceClient).
     */
    public String encodePassword(String rawPassword) {
        if (rawPassword == null || rawPassword.isBlank()) return null;
        return passwordEncoder.encode(rawPassword);
    }
}
