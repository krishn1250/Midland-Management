package com.school.midland.adminservice.service.admin;


import com.school.midland.adminservice.client.dto.UserCreationRequest;
import com.school.midland.adminservice.client.dto.UserCreationResponse;
import com.school.midland.adminservice.client.service.UserServiceClient;
import com.school.midland.adminservice.dto.AdminDto;
import com.school.midland.adminservice.mapper.AdminMapper;
import com.school.midland.adminservice.models.Admin;
import com.school.midland.adminservice.repository.AdminRepository;
import com.school.midland.constants.Role;
import com.school.midland.utils.UuidGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final UserServiceClient usersServiceClient;

    @Override
    public AdminDto createAdmin(AdminDto dto) {
        if (dto == null) throw new IllegalArgumentException("Admin DTO is null");
        UUID adminUid= UuidGenerator.generate();
        UserCreationResponse userResp = usersServiceClient.createUser(
                UserCreationRequest.builder()
                        .username(dto.getUserName())
                        .password(dto.getPassword())  // NOTE: Plain, user-service hashes
                        .role(Role.ADMIN.name())
                        .email(dto.getEmail())
                        .phoneNumber(dto.getPhoneNumber())
                        .associatedIdentifier("ADM"+adminUid.toString().substring(0,6)) // or use adminUid after creation
                        .build()
        );
        Admin admin = Admin.builder()
                .fullName(dto.getFullName())
                .firstName(dto.getFullName().split(" ")[0])
                .lastName(dto.getFullName().split(" ").length > 1 ? dto.getFullName().split(" ")[1] : null)
                .password(dto.getPassword())
                .username(dto.getUserName())
                .adminUid(adminUid)
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .designation(dto.getDesignation())
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();



//        UserCreationRequest userReq= UserCreationRequest.builder()
//                .username(dto.getUserName())
//                .password(dto.getPassword()) // Plain for now; user-service will hash
//                .role("ADMIN")
//                .associatedIdentifier(admin.getAdminUid().toString())
//                .build();

        admin.setUserUid(userResp.getUserUid());
        admin = adminRepository.save(admin);

dto.setAdminUid(admin.getAdminUid());
        dto.setUserUid(userResp.getUserUid());
        return dto;


    }

    @Override
    public AdminDto getAdmin(UUID adminUid) {
        Admin admin=adminRepository.findByAdminUid(adminUid)
                .orElseThrow(()-> new RuntimeException("Admin not found"));
        return mapToDto(admin);
    }


    @Override
    public List<AdminDto> getAllAdmins() {
        return adminRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    @Override
    public AdminDto updateAdmin(UUID adminUid, AdminDto dto) {
        Admin admin = adminRepository.findByAdminUid(adminUid)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        admin.setFullName(dto.getFullName());
        admin.setEmail(dto.getEmail());
        admin.setPhoneNumber(dto.getPhoneNumber());
        admin.setDesignation(dto.getDesignation());
        admin.setUpdatedAt(LocalDateTime.now());

        return mapToDto(adminRepository.save(admin));
    }
    @Override
    public void deleteAdmin(UUID adminUid) {
        Admin admin = adminRepository.findByAdminUid(adminUid)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        admin.setIsActive(false);
        admin.setDeletedAt(LocalDateTime.now());
        adminRepository.save(admin);
    }
    private AdminDto mapToDto(Admin admin) {
        return AdminDto.builder()
                .adminUid(admin.getAdminUid())
                .userUid(admin.getUserUid())
                .fullName(admin.getFullName())
                .email(admin.getEmail())
                .phoneNumber(admin.getPhoneNumber())
                .designation(admin.getDesignation())
                .isActive(admin.getIsActive())
                .build();
    }


}

