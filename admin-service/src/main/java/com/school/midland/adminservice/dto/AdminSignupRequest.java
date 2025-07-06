package com.school.midland.adminservice.dto;

import lombok.Data;

@Data
public class AdminSignupRequest {
    private String username;
    private String fullName;
    private String password;
    private String email;
    private String designation;
    private String phone;


}