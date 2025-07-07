package com.school.midland.adminservice.controller;


import com.school.midland.adminservice.dto.AdminLoginRequest;
import com.school.midland.adminservice.dto.AdminSignupRequest;
import com.school.midland.adminservice.dto.AuthResponse;
import com.school.midland.adminservice.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("midland/admin/auth")
@RequiredArgsConstructor
public class AdminAuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody AdminSignupRequest request) {
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody AdminLoginRequest request) {
        return ResponseEntity.ok(authService.signin(request));
    }
}
