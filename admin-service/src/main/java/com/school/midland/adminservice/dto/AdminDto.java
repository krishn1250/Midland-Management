package com.school.midland.adminservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    private UUID adminUid;
    private  UUID userUid;
    private String password;
    private String userName;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String designation;
    private boolean isActive;
}
