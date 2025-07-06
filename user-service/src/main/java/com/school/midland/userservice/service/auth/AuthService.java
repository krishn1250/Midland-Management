package com.school.midland.userservice.service.auth;

import com.school.midland.userservice.dto.AuthResponse;
import com.school.midland.userservice.dto.LoginRequest;
import com.school.midland.userservice.dto.SignupRequest;

public interface AuthService {
    AuthResponse signup(SignupRequest request);
    AuthResponse signin(LoginRequest request);
}
