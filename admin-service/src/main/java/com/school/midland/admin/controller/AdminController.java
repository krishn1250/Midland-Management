package com.school.midland.admin.controller;

import com.school.midland.admin.client.auth.dto.RegisterResponse;
import com.school.midland.admin.dtos.admin.AdminDto;
import com.school.midland.admin.dtos.admin.AdminResponseDto;
import com.school.midland.admin.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/midland/admin")
public class AdminController {

    private final AdminService adminService;


    @PostMapping("/create")
    public ResponseEntity<RegisterResponse> createAdmin(@RequestBody AdminDto adminDto,
                                                        @RequestHeader("Authorization") String authHeader) {
        final var admin = adminService.createAdmin(adminDto, authHeader);
        return ResponseEntity.ok(admin);
    }


    @GetMapping("/username/{username}")
    public ResponseEntity<AdminResponseDto> getAdminByUsername(@PathVariable String username) {
        return ResponseEntity.ok(adminService.getAdminByUsername(username));
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<AdminResponseDto> getAdminByEmail(@PathVariable String email) {
        return ResponseEntity.ok(adminService.getAdminByemail(email));
    }


    @GetMapping("/all")
    public ResponseEntity<List<AdminResponseDto>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    // ✅ Update admin
    @PutMapping("/update/{email}")
    public ResponseEntity<AdminResponseDto> updateAdmin(@PathVariable String email, @RequestBody AdminDto adminDto) {
        return ResponseEntity.ok(adminService.updateAdmin(email, adminDto));
    }

    // ✅ Delete admin
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Boolean> deleteAdmin(@PathVariable String email) {
        adminService.deleteAdmin(email);
        return ResponseEntity.ok(true);
    }
}
