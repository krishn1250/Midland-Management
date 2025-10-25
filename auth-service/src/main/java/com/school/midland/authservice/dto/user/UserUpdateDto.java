package com.school.midland.authservice.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateDto {
    private String username;
    private String password;
    //    private UUID userUid;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String role;
}
