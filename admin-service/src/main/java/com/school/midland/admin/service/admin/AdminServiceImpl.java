package com.school.midland.admin.service.admin;

import com.school.midland.admin.client.auth.AuthServiceClient;
import com.school.midland.admin.client.auth.dto.RegisterRequest;
import com.school.midland.admin.client.auth.dto.RegisterResponse;
import com.school.midland.admin.constants.Role;
import com.school.midland.admin.dtos.admin.AdminDto;
import com.school.midland.admin.dtos.admin.AdminResponseDto;
import com.school.midland.admin.exception.AuthException;
import com.school.midland.admin.models.Admin;
import com.school.midland.admin.exception.AdminException;
import com.school.midland.admin.mapper.AdminMapper;
import com.school.midland.admin.repo.AdminRepository;
import com.school.midland.admin.validator.admin.AdminValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final AuthServiceClient authServiceClient;
    private final AdminValidator adminValidator;

    @Override
    public RegisterResponse createAdmin(AdminDto dto, String authHeader) {
        try {
            adminValidator.validateCreateAdmin(dto);

            Admin admin = adminMapper.dtoToEntity(dto);
            admin.setAdminUid(UUID.randomUUID());

            // 🔹 Call Auth Service
            RegisterResponse registerResponse = authServiceClient.register(
                    RegisterRequest.builder()
                            .username(dto.getUsername())
                            .password(dto.getPassword())
                            .role(Role.ADMIN.name())
                            .email(dto.getEmail())
                            .phoneNumber(dto.getPhoneNumber())
                            .fullName(dto.getFullName())
                            .build(),
                    authHeader
            );

            if (registerResponse == null)
                throw new AuthException("Failed to register user in Auth Service", HttpStatus.BAD_REQUEST);

            admin.setUserUid(registerResponse.getUserUid());
            try{
                Admin adminres= adminRepository.save(admin);
            }
            catch (Exception e){
                authServiceClient.deleteUser(dto.getEmail(),authHeader);
                throw new AdminException("Failed to register user in Admin table", HttpStatus.BAD_REQUEST);
            }



            return registerResponse;
        }  catch (Exception e) {

            throw new AdminException("Failed to create admin: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AdminResponseDto getAdminByUsername(String username) {
        return adminRepository.findByUsername(username)
                .map(adminMapper::toResponseDto)
                .orElseThrow(() -> new AdminException("Admin not found with username: " + username, HttpStatus.NOT_FOUND));
    }

    @Override
    public AdminResponseDto getAdminByemail(String email) {
        return adminRepository.findByEmail(email)
                .map(adminMapper::toResponseDto)
                .orElseThrow(() -> new AdminException("Admin not found with email: " + email, HttpStatus.NOT_FOUND));
    }

    @Override
    public List<AdminResponseDto> getAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(adminMapper::toResponseDto)
                .toList();
    }

    @Override
    public AdminResponseDto updateAdmin(String email, AdminDto dto) {
        var existing = adminRepository.findByEmail(email)
                .orElseThrow(() -> new AdminException("Admin not found with email: " + email, HttpStatus.NO_CONTENT));

        adminMapper.updateEntityFromDto(dto, existing);
        adminRepository.save(existing);
        return adminMapper.toResponseDto(existing);
    }

    @Override
    public void deleteAdmin(String email) {
        var existing = adminRepository.findByEmail(email)
                .orElseThrow(() -> new AdminException("Admin not found with email: " + email, HttpStatus.NO_CONTENT));

        adminRepository.delete(existing);
    }
}
