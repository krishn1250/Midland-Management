package com.school.midland.adminservice.controller;

import com.school.midland.adminservice.client.dtos.UserCreationResponse;
import com.school.midland.adminservice.dtos.AdminDto;
import com.school.midland.adminservice.dtos.AdminResponse;
import com.school.midland.adminservice.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/midland/admins")
public class AdminController {

    private final AdminService adminService;


    @PostMapping("/create")
    public ResponseEntity<UserCreationResponse> createAdmin(@RequestBody AdminDto adminDto) {
        System.out.println("hello bros");
        final var admin = adminService.createAdmin(adminDto);
        return ResponseEntity.ok(admin);
    }


    @PutMapping("/update/{email}")
    public ResponseEntity<AdminResponse> updateAdmin(@PathVariable String email, @RequestBody AdminDto adminDto) {
        final var updatedAdmin = adminService.updateAdmin(email, adminDto);
        return ResponseEntity.ok(updatedAdmin);
    }


    @GetMapping("/username/{username}")
    public ResponseEntity<AdminResponse> getAdminByUsername(@PathVariable String username) {
        return ResponseEntity.ok(adminService.getAdminByUsername(username));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AdminResponse> getAdminByEmail(@PathVariable String email) {
        return ResponseEntity.ok(adminService.getAdminByEmail(email));
    }
    // ✅ Get all admins
    @GetMapping("/all")
    public ResponseEntity<List<AdminResponse>> getAllAdmins() {
        final var admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    // ✅ Delete admin (soft delete)
    @DeleteMapping("delete/{email}")
    public ResponseEntity<String> deleteAdmin(@PathVariable String email,@RequestHeader("Authorization")String token) {
        adminService.deleteAdmin(email,token);
        return ResponseEntity.ok("deleted sucessfully");
    }


    }
