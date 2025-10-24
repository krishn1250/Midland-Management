package com.school.midland.adminservice.service.admin;

import com.school.midland.adminservice.client.dtos.UserCreationResponse;
import com.school.midland.adminservice.dtos.AdminDto;
import com.school.midland.adminservice.dtos.AdminResponse;
import com.school.midland.adminservice.models.Admin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdminService {

   UserCreationResponse createAdmin(AdminDto dto);
    AdminResponse getAdmin(String username);
    List<AdminResponse> getAllAdmins();
    AdminDto updateAdmin(UUID adminUid, AdminDto dto);
    void deleteAdmin(UUID adminUid);
}
