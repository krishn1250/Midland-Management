package com.school.midland.adminservice.init;


import com.school.midland.adminservice.models.Admin;
import com.school.midland.adminservice.repository.AdminRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DefaultAdminInitializer {
    private final AdminRepository adminRepository;
    private final UserServiceClient userServiceClient;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createDefaultAdmin(){
        String defaultUsername = "admin";
        String defaultPassword = "admin123"; // Secure it in production!
        String defaultEmail = "admin@midland.com";

        if (adminRepository.findByUsername(defaultUsername).isPresent()) {
            log.info(" Default admin already exists.");
            return;
        }

        UUID adminUid = UUID.randomUUID();
        String associatedIdentifier = "ADM" + adminUid.toString().substring(0, 6);

        UserCreationRequest userReq = UserCreationRequest.builder()
                .username(defaultUsername)
                .password(passwordEncoder.encode(defaultPassword))
                .role(Role.ADMIN.name())
                .associatedIdentifier(associatedIdentifier)
                .build();

        UUID userResp = userServiceClient.createUser(userReq);
        Admin admin = Admin.builder()
                .adminUid(adminUid)
                .userUid(userResp)
                .username(defaultUsername)
                .password(passwordEncoder.encode(defaultPassword))
                .fullName("Default Admin")
                .firstName("Default")
                .email(defaultEmail)
                .designation("System Admin")
                .isActive(true)
                .build();
        adminRepository.save(admin);
        log.info(" Default admin created: username=admin, password=admin123");

    }
}
