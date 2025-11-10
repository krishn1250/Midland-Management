package com.school.midland.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransportDto{
    private String admissionNumber;
    private String pickupLocation;
    private String dropLocation;
    private String routeNumber;
    private String vehicleNumber;
    private String driverName;
    private String driverContact;
    private Boolean isActive;
}