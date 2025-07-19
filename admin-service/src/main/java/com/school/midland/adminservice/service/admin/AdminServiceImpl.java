package com.school.midland.adminservice.service.admin;

import com.school.midland.adminservice.client.dtos.UserCreationRequest;
import com.school.midland.adminservice.client.dtos.UserCreationResponse;
import com.school.midland.adminservice.client.service.auth.AuthServiceClient;
import com.school.midland.adminservice.dtos.AdminDto;
import com.school.midland.adminservice.mapper.AdminMapper;
import com.school.midland.adminservice.models.Admin;
import com.school.midland.adminservice.repository.AdminRepository;
import com.school.midland.commonlib.constants.UserRole;
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
    private final AuthServiceClient authServiceClient;


    @Override
    public UserCreationResponse createAdmin(AdminDto dto) {
        if (dto == null) throw new IllegalArgumentException("Admin DTO is null");

         var admin = adminMapper.requesttoEntity(dto);

        UserCreationResponse userResp = authServiceClient.createUser(
                UserCreationRequest.builder()
                        .username(dto.getUserName())
                        .password(dto.getPassword())  // NOTE: Plain, user-service hashes
                        .role(String.valueOf(UserRole.ADMIN.name()))
                        .email(dto.getEmail())
                        .phoneNumber(dto.getPhoneNumber())
                        .associatedIdentifier("ADM"+admin.getAdminUid().toString().substring(0,6)) // or use adminUid after creation
                        .build()
        );
        if(userResp==null){
            throw new IllegalArgumentException("unable to create signup");
        }
        admin.setUserUid(userResp.getUserUid());
        adminRepository.save(admin);

        return userResp;


    }

    @Override
    public AdminDto getAdmin(String username, String password) {
        return null;
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

                .fullName(admin.getFullName())
                .email(admin.getEmail())
                .phoneNumber(admin.getPhoneNumber())
                .designation(admin.getDesignation())

                .build();
    }


}


