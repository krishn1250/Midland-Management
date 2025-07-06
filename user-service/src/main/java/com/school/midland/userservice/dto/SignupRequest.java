package com.school.midland.userservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class SignupRequest {

    private String username;
    private String password;
    private  String email;
    private String role;
    private String associatedIdentifier;
}
