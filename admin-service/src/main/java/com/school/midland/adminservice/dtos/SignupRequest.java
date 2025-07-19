package com.school.midland.adminservice.dtos;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String fullName;
    private String password;
    private String email;
    private String designation;
    private String phone;
}
