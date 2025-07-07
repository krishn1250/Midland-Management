package com.school.midland.adminservice.controller;


import com.school.midland.adminservice.dto.AdminDto;
import com.school.midland.adminservice.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/midland/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get")
    public String greetings(){
        return "hello bros";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<AdminDto> createAdmin(@RequestBody AdminDto dto) {
        return ResponseEntity.ok(adminService.createAdmin(dto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{adminUid}")
    public ResponseEntity<AdminDto> getAdmin(@PathVariable UUID adminUid) {
        return ResponseEntity.ok(adminService.getAdmin(adminUid));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<List<AdminDto>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{adminUid}")
    public ResponseEntity<AdminDto> updateAdmin(@PathVariable UUID adminUid, @RequestBody AdminDto dto) {
        return ResponseEntity.ok(adminService.updateAdmin(adminUid, dto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{adminUid}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable UUID adminUid) {
        adminService.deleteAdmin(adminUid);
        return ResponseEntity.noContent().build();
    }

}
