package com.school.midland.admin.dtos.auth;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter @Getter
public class AuthRequest {
    private String username;
    private String password;
    private String fullName;
    private String role;
    private String email;
    private  String phoneNumber;
}
