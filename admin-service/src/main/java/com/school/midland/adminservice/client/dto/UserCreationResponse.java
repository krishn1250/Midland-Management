package com.school.midland.adminservice.client.dto;

import lombok.Data;

@Data
public class UserCreationResponse {
    private String token;
    private String username;
}
