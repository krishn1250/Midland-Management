package com.school.midland.admin.service.admin;

import com.school.midland.admin.client.auth.dto.RegisterResponse;
import com.school.midland.admin.dtos.admin.AdminDto;
import com.school.midland.admin.dtos.admin.AdminResponseDto;

import java.util.List;

public interface AdminService {
    RegisterResponse createAdmin(AdminDto dto,String authHeader);
    AdminResponseDto getAdminByUsername(String username);
    AdminResponseDto getAdminByemail(String email);
    List<AdminResponseDto> getAllAdmins();
    AdminResponseDto updateAdmin(String email, AdminDto dto);
    void deleteAdmin(String email);
}
