package com.school.midland.adminservice.mapper;


import com.school.midland.adminservice.dtos.AdminDto;
import com.school.midland.adminservice.models.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AdminMapper {
    private final PasswordEncoder passwordEncoder;
    public Admin requesttoEntity(AdminDto request) {
        String[] parts = request.getFullName().split(" ", 2);
        return Admin.builder()
                .adminUid(UUID.randomUUID())
                .username(request.getUserName())
                .firstName(parts[0])
                .lastName(parts.length>1 ? parts[1]:"")
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())

                .isActive(true)
                .build();
    }
    public AdminDto toDto(Admin admin) {
        return AdminDto.builder()
                .userName(admin.getUsername())
                .fullName(admin.getFullName())
                .email(admin.getEmail())
                .phoneNumber(admin.getPhoneNumber())
                .designation(admin.getDesignation())
                .build();
    }
}

