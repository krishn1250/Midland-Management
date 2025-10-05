package com.school.midland.adminservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationRequest {
    private String username;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String role;
    private String associatedIdentifier; // schoolCode or adminId
}