package com.school.midland.admin.client.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class LoginRequest {
    private String username;
    private String password;
    private String role;
    private String email;

}
