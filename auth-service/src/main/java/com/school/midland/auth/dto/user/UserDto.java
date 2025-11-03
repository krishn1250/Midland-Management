package com.school.midland.auth.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Data
public class UserDto {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private  String userUid;
    private String role;
    private  String phoneNumber;
    private String isActive;
}