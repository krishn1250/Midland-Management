package com.school.midland.adminservice.dto;

import lombok.Data;

@Data
public class AdminLoginRequest {
    private String username;
    private String email;
    private String password;
}