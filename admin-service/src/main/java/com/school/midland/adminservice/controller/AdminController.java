package com.school.midland.adminservice.controller;

import com.school.midland.adminservice.client.dtos.UserCreationResponse;
import com.school.midland.adminservice.dtos.AdminDto;
import com.school.midland.adminservice.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/midland/admins")
public class AdminController {

    private final AdminService adminService;
    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(@RequestBody AdminDto adminDto) {
        final var admin = adminService.createAdmin(adminDto);
        return ResponseEntity.ok(admin);
    }

//    @PutMapping("/{adminId}")
//    public ResponseEntity<?> updateAdmin(@PathVariable Long adminId, @RequestBody AdminDto adminDto) {
//        // logic to update admin details
//    }
//
//    @GetMapping("/{adminId}")
//    public ResponseEntity<?> getAdminDetails(@PathVariable Long adminId) {
//        // fetch admin details
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<AdminDto>> getAllAdmins() {
//        // list all admins
//    }
//
//    @DeleteMapping("/{adminId}")
//    public ResponseEntity<?> deleteAdmin(@PathVariable Long adminId) {
//        // delete admin account (soft delete or deactivate)
//    }
//
//    @PutMapping("/change-status/{adminId}")
//    public ResponseEntity<?> toggleAdminStatus(@PathVariable Long adminId, @RequestParam boolean active) {
//        // enable/disable admin account
//    }
}
