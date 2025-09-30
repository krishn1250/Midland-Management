package com.school.midland.adminservice;

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
    CommandLineRunner initDefaultAdmin(AdminRepository adminRepository) {
        return args -> {
            // Check if admin already exists
            adminRepository.findByUsername("superadmin")
                    .or(() -> adminRepository.findByEmail("admin@midland.edu"))
                    .ifPresentOrElse(
                            existing -> System.out.println("✅ Default admin already exists: " + existing.getUsername()),
                            () -> {
                                Admin admin = Admin.builder()
                                        .userUid(UUID.randomUUID()) // ideally should come from users table
                                        .username("superadmin")
                                        .email("admin@midland.edu")
                                        .fullName("System Administrator")
                                        .designation("System Admin")
                                        .schoolCode("MID-HYD-001") // default school code
                                        .isActive(true)
                                        .build();

                                adminRepository.save(admin);
                                System.out.println("🚀 Default admin created: superadmin / admin@midland.edu");
                            }
                    );
        };
    }


}
