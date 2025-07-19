package com.school.midland.adminservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {

    @NotBlank(message = "Password is mandatory")
    private String password;
    @NotBlank(message = "Username is mandatory")
    private String userName;
    private String fullName;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    private String phoneNumber;
    private String designation;

}
