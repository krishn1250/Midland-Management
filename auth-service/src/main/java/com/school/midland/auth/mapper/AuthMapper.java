package com.school.midland.auth.mapper;

import com.school.midland.auth.dto.request.RegisterRequest;
import com.school.midland.auth.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthMapper {



    public  User toUserEntity(RegisterRequest request, String passwordEncoded) {

        return   User.builder()
                .email(request.getEmail())
                .userUid(UUID.randomUUID())
                .associatedIdentifier(request.getRole()+request.getUsername())
                .fullName(request.getFullName())
                .role(request.getRole())
                .isActive(Boolean.TRUE)
                .password(passwordEncoded)
                .username(request.getUsername())
                .phoneNumber(request.getPhoneNumber())
                .createdAt(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .build(); }



}
