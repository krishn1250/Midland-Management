package com.school.midland.admin.dtos.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Setter @Getter
public class AuthResponse {
    private String token;
    private String username;
    private String fullName;
    private String email;
    private String role;
    private String message;
}
