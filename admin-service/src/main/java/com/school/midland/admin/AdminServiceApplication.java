package com.school.midland.admin;

import com.school.midland.admin.client.auth.AuthServiceClient;
import com.school.midland.admin.client.auth.dto.RegisterRequest;
import com.school.midland.admin.client.auth.dto.RegisterResponse;
import com.school.midland.admin.client.auth.dto.UserDto;
import com.school.midland.admin.models.Admin;
import com.school.midland.admin.repo.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@SpringBootApplication
public class AdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner initDefaultAdmin(AdminRepository adminRepository,
                                       AuthServiceClient authServiceClient) {
        return args -> {
            try {
                log.info("🔍 Checking if superadmin exists in auth-service...");
                UserDto response = null;
                RegisterResponse authResponse = null;

                // Step 1: Try fetching the user from auth-service
                try {
                    response = authServiceClient.getbyuserName("superadmin");
                    log.info("✅ Superadmin already exists in auth-service: {}", response.getUsername());
                    if(response!=null)
                        return;

                } catch (WebClientResponseException.NotFound e) {
                    log.warn("⚠️ No superadmin found in auth-service, creating one...");
                }

                // Step 2: If not found, create a new one
                if (response == null) {
                    String adminUsername = System.getenv().getOrDefault("FIRST_ADMIN_USERNAME", "superadmin");
                    String adminPassword = System.getenv().getOrDefault("FIRST_ADMIN_PASSWORD", "superadmin@123");
                    String adminEmail = System.getenv().getOrDefault("FIRST_ADMIN_EMAIL", "admin@midland.edu");


                    RegisterRequest registerRequest = RegisterRequest.builder()
                            .username(adminUsername)
                            .password(adminPassword)
                            .fullName("System Administrator")
                            .email(adminEmail)
                            .role("ADMIN")
                            .phoneNumber("98989")
                            .associatedIdentifier("ADM1")
                            .build();

                    authResponse = authServiceClient.createAdmin(registerRequest);
                    log.info("🚀 Superadmin successfully created in auth-service: {}", authResponse.getUsername());
                }

                // Step 3: Validate authResponse or existing user
                if (authResponse == null && response == null) {
                    log.error(" Could not determine email/username for superadmin, skipping admin creation.");
                    return;
                }


                // Step 4: Use whichever exists (authResponse or response)
                String username = (authResponse != null) ? authResponse.getUsername() : response.getUsername();
                String email = (authResponse != null) ? authResponse.getEmail() : response.getEmail();
                String fullName = (authResponse != null) ? authResponse.getFullName() : response.getFullName();
                String phone = (authResponse != null) ? authResponse.getPhoneNumber() : response.getPhoneNumber();
                UUID userUid = authResponse.getUserUid();

                String firstName = "";
                String lastName = "";
                if (fullName != null) {
                    String[] parts = fullName.trim().split("\\s+", 2);
                    firstName = parts.length > 0 ? parts[0] : "";
                    lastName = parts.length > 1 ? parts[1] : "";
                }

                // Step 5: Check if admin already exists in DB
                if (adminRepository.findByUsername(username).isPresent()) {

                    log.info(" Superadmin already exists in admin DB.");
                    return;
                } else {
                    Admin admin = Admin.builder()
                            .adminUid(UUID.randomUUID())
                            .username(username)
                            .email(email)
                            .userUid(userUid)
                            .fullName(fullName)
                            .designation("System Admin")
                            .phoneNumber(phone)
                            .firstName(firstName)
                            .lastName(lastName)
                            .schoolCode("MID-HYD-001")
                            .isActive(true)
                            .createdAt(LocalDateTime.now())

                            .build();

                    log.info("🧩 Creating local admin entry for superadmin...");
                    adminRepository.save(admin);
                }

                log.info(" Default admin ensured in both auth-service and admin-service.");

            } catch (Exception e) {
                log.error(" Error initializing default admin: ", e);
            }
        };
    }
}

