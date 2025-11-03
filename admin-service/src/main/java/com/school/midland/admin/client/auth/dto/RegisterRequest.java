package com.school.midland.admin.client.auth.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String role;
    private  String phoneNumber;
    private String associatedIdentifier;
}
