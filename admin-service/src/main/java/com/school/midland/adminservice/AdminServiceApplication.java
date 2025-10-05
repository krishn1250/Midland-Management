package com.school.midland.adminservice;

import com.school.midland.adminservice.client.dtos.UserCreationRequest;
import com.school.midland.adminservice.client.dtos.UserCreationResponse;
import com.school.midland.adminservice.client.service.auth.AuthServiceClient;

import com.school.midland.adminservice.cons.Role;
import com.school.midland.adminservice.models.Admin;
import com.school.midland.adminservice.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.UUID;

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
                UserCreationResponse response = null;
                try {
                    response = authServiceClient.getbyuserName("superadmin");
                    System.out.println(response);
                } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
                    System.out.println("User not found in auth-service, creating new one...");
                }

                if (response == null) {
                    UserCreationRequest request = UserCreationRequest.builder()
                            .username("superadmin")
                            .password("superadmin@123")
                            .fullName("System Administrator")
                            .email("admin@midland.edu")
                            .role("ADMIN")
                            .phoneNumber("")
                            .associatedIdentifier("ADM")
                            .build();
                    response = authServiceClient.createUser(request);
                }

                UserCreationResponse finalResponse = response;
                System.out.println(finalResponse);
                adminRepository.findByUserUid(finalResponse.getUserUid())
                        .orElseGet(() -> {
                            Admin admin = Admin.builder()
                                    .userUid(finalResponse.getUserUid())
                                    .username(finalResponse.getUsername())
                                    .email(finalResponse.getEmail())
                                    .fullName("System Administrator")
                                    .designation("System Admin")
                                    .schoolCode("MID-HYD-001")
                                    .isActive(true)
                                    .build();
                            return adminRepository.save(admin);
                        });

                System.out.println("🚀 Default admin ensured in both auth-service and admin-service");

            } catch (Exception e) {
                System.err.println("❌ Failed to create default admin: " + e.getMessage());
            }
        };
    }

}
