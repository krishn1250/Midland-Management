package com.school.midland.adminservice.client.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class UserCreationResponse {
    private String token;
    private String username;
    private UUID userUid;
    private String role;
    private String message;
}
