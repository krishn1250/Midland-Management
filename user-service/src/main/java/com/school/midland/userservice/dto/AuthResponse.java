package com.school.midland.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.school.midland.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponse {
    private String token;
    private String Username;
    private String role;
    private String message;
}