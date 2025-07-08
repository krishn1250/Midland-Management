package com.school.midland.userservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Section {
    @Id
    private String id;
    private String name;

    @ManyToMany(mappedBy = "sections")
    private List<Teacher> teachers;

    @ManyToMany
    @JoinTable(
            name = "section_subjects",
            joinColumns = @JoinColumn(name = "section_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;


}
