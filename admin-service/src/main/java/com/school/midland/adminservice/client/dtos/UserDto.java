package com.school.midland.adminservice.client.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserDto {
//    private String token;
    private String username;
//    private UUID userUid;
    private String fullName;
    private String email;
    private  String phoneNumber;
    private String role;
//    private String message;
}
