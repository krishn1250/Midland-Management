package com.school.midland.auth.controller;


import com.school.midland.auth.constants.AuthApi;
import com.school.midland.auth.dto.request.LoginRequest;
import com.school.midland.auth.dto.request.RegisterRequest;
import com.school.midland.auth.dto.response.LoginResponse;
import com.school.midland.auth.dto.response.UserCreationResponse;
import com.school.midland.auth.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AuthApi.AUTH_URI)
public class AuthController {

    private  final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<UserCreationResponse>  register(@RequestBody @Valid RegisterRequest request,
                                                          @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(authService.userSignup(request));
    }
    @PostMapping("/student/login")
    public ResponseEntity<LoginResponse> parentLogin(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.userSignin(request, "STUDENT"));
    }

    @PostMapping("/teacher/login")
    public ResponseEntity<LoginResponse> teacherLogin(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.userSignin(request, "TEACHER"));
    }

    @PostMapping("/admin/login")
    public ResponseEntity<LoginResponse> adminLogin(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.userSignin(request, "ADMIN"));
    }

    @PostMapping("/superAdmin")
    public ResponseEntity<UserCreationResponse>  superAdminRegister( @Valid @RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.userSignup(request));
    }



}
