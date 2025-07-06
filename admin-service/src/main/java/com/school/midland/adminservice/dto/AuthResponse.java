package com.school.midland.adminservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthResponse {
    private final String token;
    private final String role;
    private final String message;

}
