package com.school.midland.admin.dtos.admin;



import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDto {

    private Long adminId;
    private UUID adminUid;
    private UUID userUid;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
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

