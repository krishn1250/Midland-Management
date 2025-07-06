package com.school.midland.userservice.controller;

import com.school.midland.userservice.dto.AuthResponse;
import com.school.midland.userservice.dto.LoginRequest;
import com.school.midland.userservice.dto.SignupRequest;
import com.school.midland.userservice.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/midland/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest request) {
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.signin(request));
    }
    @GetMapping("/huhu")
    public String hi(){
        return "hello";
    }
}
