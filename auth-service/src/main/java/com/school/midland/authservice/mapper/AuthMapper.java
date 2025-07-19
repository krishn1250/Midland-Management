package com.school.midland.authservice.mapper;

import com.school.midland.authservice.dto.request.RegisterRequest;
import com.school.midland.authservice.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthMapper {



    public  User toUserEntity(RegisterRequest request, String passwordEncoded) {

        return   User.builder()
                .email(request.getEmail())
                .userUid(UUID.randomUUID())
                .associatedIdentifier(request.getAssociatedIdentifier())
                .fullName(request.getFullName())
                .role(request.getRole())
                .password(passwordEncoded)
                .username(request.getUsername())
                .build(); }



}
