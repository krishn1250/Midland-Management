package com.school.midland.adminservice.service.admin;


import com.school.midland.adminservice.dto.AdminDto;

import java.util.List;
import java.util.UUID;

public interface AdminService {
    AdminDto createAdmin(AdminDto dto);
    AdminDto getAdmin(UUID adminUid);
    List<AdminDto> getAllAdmins();
    AdminDto updateAdmin(UUID adminUid, AdminDto dto);
    void deleteAdmin(UUID adminUid);
}
