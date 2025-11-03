package com.school.midland.admin.dtos.school;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolDto {

    private Long schoolId;
    private UUID schoolUid;
    private String schoolCode;
    private String schoolName;
    private String location;
    private String address;
    private String city;
    private String state;
    private String country;
    private String pinCode;
    private String phoneNumber;
    private String email;
    private String website;
    private String timezone;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}