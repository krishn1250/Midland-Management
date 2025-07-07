package com.school.midland.adminservice.client.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserCreationResponse {
    private String token;
    private String username;
    private UUID userUid;
}
