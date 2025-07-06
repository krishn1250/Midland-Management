package com.studentmanagement.test02.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record StudentDTO(
    String suuid,
    
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    String name,
    
    @NotBlank(message = "Roll number is required")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "Roll number must contain only uppercase letters and numbers")
    String rollNumber,
    
    @Past(message = "Date of birth must be in the past")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate dateOfBirth,
    
    @Email(message = "Please provide a valid email address")
    String email,
    
    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Phone number must be 10-15 digits")
    String phone,
    
    @Size(max = 500, message = "Address cannot exceed 500 characters")
    String address,
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate admissionDate,
    
    SectionDTO section // Use the simplified SectionDTO instead of full Section entity
) {}