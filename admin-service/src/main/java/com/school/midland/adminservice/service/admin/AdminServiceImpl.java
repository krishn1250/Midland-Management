    package com.school.midland.adminservice.service.admin;

    import com.school.midland.adminservice.client.dtos.UserCreationRequest;
    import com.school.midland.adminservice.client.dtos.UserCreationResponse;
    import com.school.midland.adminservice.client.dtos.UserDto;
    import com.school.midland.adminservice.client.service.auth.AuthServiceClient;
    import com.school.midland.adminservice.dtos.AdminDto;
    import com.school.midland.adminservice.dtos.AdminResponse;
    import com.school.midland.adminservice.mapper.AdminMapper;
    import com.school.midland.adminservice.models.Admin;
    import com.school.midland.adminservice.repository.AdminRepository;
    import com.school.midland.commonlib.constants.UserRole;
    import com.school.midland.commonlib.exception.AdminException;
    import com.school.midland.commonlib.exception.UserException;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.web.client.HttpClientErrorException;
    import org.springframework.web.client.RestClientException;

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
            admin.setAdminUid(UUID.randomUUID());
            UserCreationResponse userResp = authServiceClient.createUser(
                    UserCreationRequest.builder()
                            .username(dto.getUsername())
                            .password(dto.getPassword())  // NOTE: Plain, user-service hashes
                            .role(String.valueOf(UserRole.ADMIN.name()))
                            .email(dto.getEmail())
                            .fullName(dto.getFullName())
//                            .phoneNumber(dto.getPhoneNumber())
                            .associatedIdentifier("ADM" + admin.getAdminUid().toString().substring(0, 6)) // or use adminUid after creation
                            .build()
            );
            if (userResp == null) {
                throw new IllegalArgumentException("unable to create signup");
            }
            admin.setUserUid(userResp.getUserUid());
            admin.setSchoolCode(dto.getSchoolCode());
            adminRepository.save(admin);

            return userResp;


        }

        @Override
        public AdminResponse getAdmin(String username) {
            Admin admin = adminRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Admin not found"));
            return adminMapper.mapToDtoResponse(admin);
        }


        @Override
        public List<AdminResponse> getAllAdmins() {
            return adminRepository.findAll().stream()
                    .map(admin->adminMapper.mapToDtoResponse(admin))
                    .collect(Collectors.toList());
        }

        @Override
        public AdminResponse updateAdmin(String email, AdminDto dto) {
            Admin admin = adminRepository.findByEmail(email)
                    .orElseThrow(() -> new AdminException("user not found ", HttpStatus.NOT_FOUND));

            if (dto.getFullName() != null) {
                admin.setFullName(dto.getFullName());
                String[] parts = dto.getFullName().trim().split("\\s+");
                admin.setFirstName(parts[0]);
                admin.setLastName(parts[parts.length - 1]);
            }
            if (dto.getPhoneNumber() != null) {
                admin.setPhoneNumber(dto.getPhoneNumber());
            }
            if (dto.getDesignation() != null) {
                admin.setDesignation(dto.getDesignation());
            }

            if (dto.getSchoolCode()!=null){
                admin.setSchoolCode(dto.getSchoolCode());
            }

            admin.setUpdatedAt(LocalDateTime.now());

            UserDto user = authServiceClient.getByEmail(email);
            if (user == null) {
                throw new UserException("user not found", HttpStatus.NOT_FOUND);
            }

            if (dto.getFullName() != null) {
                user.setFullName(dto.getFullName());
            }
            if (dto.getPhoneNumber() != null) {
                user.setPhoneNumber(dto.getPhoneNumber());
            }


            authServiceClient.updateUser(email, user);

            return adminMapper.mapToDtoResponse(adminRepository.save(admin));
        }

        @Override
        public AdminResponse getAdminByUsername(String username) {
            Admin admin = adminRepository.findAll().stream()
                    .filter(a -> username.equals(a.getUsername()))
                    .findFirst()
                    .orElseThrow(() -> new AdminException("Admin not found with username: " + username, HttpStatus.NOT_FOUND));
            return adminMapper.mapToDtoResponse(admin);
        }

        @Override
        public AdminResponse getAdminByEmail(String email) {
            Admin admin = adminRepository.findAll().stream()
                    .filter(a -> email.equals(a.getEmail()))
                    .findFirst()
                    .orElseThrow(() -> new AdminException("Admin not found with email: " + email, HttpStatus.NOT_FOUND));
            return adminMapper.mapToDtoResponse(admin);
        }

        @Override
        @Transactional
        public void deleteAdmin(String email, String token) {
            boolean deleted;
            try {
                deleted = authServiceClient.deleteUser(email, token);
            } catch (HttpClientErrorException e) {
                String response = e.getResponseBodyAsString();
                throw new UserException("auth-service error: " + response, HttpStatus.valueOf(e.getStatusCode().value()));
            } catch (RestClientException e) {
                throw new UserException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
            }

            if (!deleted) {
                throw new UserException("user not deleted in auth-service", HttpStatus.NOT_ACCEPTABLE);
            }


        }


        private AdminDto mapToDto(Admin admin) {
            return AdminDto.builder()
                    .fullName(admin.getFullName())
                    .username(admin.getUsername())
                    .email(admin.getEmail())
                    .phoneNumber(admin.getPhoneNumber())
                    .designation(admin.getDesignation())
                    .schoolCode(admin.getSchoolCode())
                    .build();
        }



    }


