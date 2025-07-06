package com.school.midland.adminservice.mapper;


import com.school.midland.adminservice.dto.AdminDto;
import com.school.midland.adminservice.dto.AdminSignupRequest;
import com.school.midland.adminservice.models.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminMapper {
    private final PasswordEncoder passwordEncoder;
    public Admin requesttoEntity(AdminSignupRequest request) {
        String[] parts = request.getFullName().split(" ", 2);
        return Admin.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(parts[0])
                .lastName(parts.length>1 ? parts[1]:"")
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phoneNumber(request.getPhone())
                .isActive(true)
                .build();
    }
    public AdminDto toDto(Admin admin) {
        return AdminDto.builder()
                .adminUid(admin.getAdminUid())
                .userUid(admin.getUserUid())
                .userName(admin.getUsername())
                .fullName(admin.getFullName())
                .email(admin.getEmail())
                .phoneNumber(admin.getPhoneNumber())
                .designation(admin.getDesignation())
                .isActive(admin.getIsActive())
                .build();
    }
}

