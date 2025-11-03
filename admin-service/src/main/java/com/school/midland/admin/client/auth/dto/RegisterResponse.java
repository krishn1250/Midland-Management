package com.school.midland.admin.client.auth.dto;

import lombok.Data;
import lombok.Getter;

import java.util.UUID;
@Data
@Getter
public class RegisterResponse {
    private String token;
    private String username;
    private UUID userUid;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String role;
    private String message;
}
