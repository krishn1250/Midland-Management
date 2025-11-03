package com.school.midland.admin.dtos.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminResponseDto { ;
    private int id;
    private String firstName;
    private String username;
    private String lastName;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String designation;
    private String profileImage;
    private String notes;
    private Boolean isActive;
    private String schoolCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
