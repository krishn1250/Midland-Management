package com.school.midland.adminservice.controller;

import com.school.midland.adminservice.client.dtos.UserCreationResponse;
import com.school.midland.adminservice.dtos.AdminDto;
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

    // ✅ Create admin
    @PostMapping("/create")
    public ResponseEntity<UserCreationResponse> createAdmin(@RequestBody AdminDto adminDto) {
        final var admin = adminService.createAdmin(adminDto);
        return ResponseEntity.ok(admin);
    }

    // ✅ Update admin
    @PutMapping("/{adminUid}")
    public ResponseEntity<AdminDto> updateAdmin(@PathVariable UUID adminUid, @RequestBody AdminDto adminDto) {
        final var updatedAdmin = adminService.updateAdmin(adminUid, adminDto);
        return ResponseEntity.ok(updatedAdmin);
    }

    // ✅ Get admin details
    @GetMapping("/{username}")
    public ResponseEntity<AdminDto> getAdminDetails(@PathVariable String  username) {
        // We’ll reuse getAllAdmins + filter OR create a getAdminByUid method in service
        return adminService.getAllAdmins().stream()
                .filter(admin -> username.equals(admin.getUsername())) // If AdminDto has UID field
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Get all admins
    @GetMapping("/all")
    public ResponseEntity<List<AdminDto>> getAllAdmins() {
        final var admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    // ✅ Delete admin (soft delete)
    @DeleteMapping("/{adminUid}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable UUID adminUid) {
        adminService.deleteAdmin(adminUid);
        return ResponseEntity.noContent().build();
    }

    // ✅ Toggle active/inactive status
//    @PutMapping("/change-status/{adminUid}")
//    public ResponseEntity<String> toggleAdminStatus(@PathVariable UUID adminUid, @RequestParam boolean active) {
//        // This assumes updateAdmin isActive logic is inside service
//        // If not, create a new service method toggleStatus(adminUid, active)
//        // For now we’ll handle inline
//        try {
//            AdminDto admin = adminService.updateAdmin(adminUid,
//                    AdminDto.builder().isActive(active).build());
//            return ResponseEntity.ok("Admin status updated to " + (active ? "Active" : "Inactive"));
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
    }
