package com.school.midland.authservice.controller;


import com.school.midland.authservice.constants.AuthApi;
import com.school.midland.authservice.dto.request.LoginRequest;
import com.school.midland.authservice.dto.request.RegisterRequest;
import com.school.midland.authservice.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AuthApi.AUTH_URI)
public class AuthController {

    private  final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?>  register(@RequestBody RegisterRequest request){
return authService.userSignup(request);
    }
    @PostMapping("/parent/login")
    public ResponseEntity<?> parentLogin(@RequestBody LoginRequest request) {
        return authService.userSignin(request, "PARENT");
    }

    @PostMapping("/teacher/login")
    public ResponseEntity<?> teacherLogin(@RequestBody LoginRequest request) {
        return authService.userSignin(request, "TEACHER");
    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody LoginRequest request) {
        return authService.userSignin(request, "ADMIN");
    }

    @PostMapping("/accountant/login")
    public ResponseEntity<?> accountantLogin(@RequestBody LoginRequest request) {
        return authService.userSignin(request, "ACCOUNTANT");
    }

    @PostMapping("/system/login")
    public ResponseEntity<?> systemLogin(@RequestBody LoginRequest request) {
        return authService.userSignin(request, "SYSTEM");
    }


}
