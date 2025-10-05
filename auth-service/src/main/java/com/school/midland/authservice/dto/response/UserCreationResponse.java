package com.school.midland.authservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserCreationResponse {
    private String token;
    private String username;
    private UUID userUid;
    private String fullName;
    private String email;
    private String role;
    private String message;


}
