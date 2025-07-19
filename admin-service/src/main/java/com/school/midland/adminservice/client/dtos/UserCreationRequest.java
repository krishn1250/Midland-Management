package com.school.midland.adminservice.client.dtos;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Setter
@Getter
public class UserCreationRequest {
    private String username;
    private String password;
    private String fullName;
    private String role;
    private String email;
    private  String phoneNumber;
    private String associatedIdentifier;
}
