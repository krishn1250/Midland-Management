package com.school.midland.adminservice.client.dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Setter
@Getter
public class UserCreationRequest {
    private String username;
    private String password;
    private String role;
    @Email
    private String email;
    private  String phoneNumber;
    private String associatedIdentifier; // adminUid
}