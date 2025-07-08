package com.school.midland.userservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    private String id;
    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;


}
