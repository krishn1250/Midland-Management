package com.school.midland.userservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransportRoute {
    @Id
    private String id;
    private String routeName;
    private String driverName;
    private String vehicleNumber;

    @ManyToMany
    @JoinTable(
            name = "transport_students",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;  // Students assigned to this route
}