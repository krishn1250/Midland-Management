package com.school.midland.adminservice.service;

import com.school.midland.adminservice.dto.AdminLoginRequest;
import com.school.midland.adminservice.dto.AdminSignupRequest;
import com.school.midland.adminservice.dto.AuthResponse;

public interface AuthService {
    AuthResponse signup(AdminSignupRequest request);
    AuthResponse signin(AdminLoginRequest request);
}
