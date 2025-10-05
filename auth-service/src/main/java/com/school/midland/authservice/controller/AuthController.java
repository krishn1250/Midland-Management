package com.school.midland.authservice.controller;


import com.school.midland.authservice.constants.AuthApi;
import com.school.midland.authservice.dto.request.LoginRequest;
import com.school.midland.authservice.dto.request.RegisterRequest;
import com.school.midland.authservice.service.auth.AuthService;
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
    public ResponseEntity<?>  register(@RequestBody RegisterRequest request){

        System.out.println("hello");
        return authService.userSignup(request);
    }
    @GetMapping("/student/login")
    public ResponseEntity<?> parentLogin(@RequestBody LoginRequest request) {
        return authService.userSignin(request, "STUDENT");
    }

    @GetMapping("/teacher/login")
    public ResponseEntity<?> teacherLogin(@RequestBody LoginRequest request) {
        return authService.userSignin(request, "TEACHER");
    }

    @GetMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody LoginRequest request) {
        return authService.userSignin(request, "ADMIN");
    }

//    @GetMapping("/accountant/login")
//    public ResponseEntity<?> accountantLogin(@RequestBody LoginRequest request) {
//        return authService.userSignin(request, "ACCOUNTANT");
//    }
//
//    @GetMapping("/system/login")
//    public ResponseEntity<?> systemLogin(@RequestBody LoginRequest request) {
//        return authService.userSignin(request, "SYSTEM");
//    }



}
