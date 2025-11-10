package com.school.midland.user.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transport")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID transportUid;

    @Column(nullable = false)
    private String admissionNumber;

    private String pickupLocation;
    private String dropLocation;
    private String routeNumber;
    private String vehicleNumber;
    private String driverName;
    private String driverContact;

    private Boolean isActive = true;
    private LocalDateTime createdAt = LocalDateTime.now();
}