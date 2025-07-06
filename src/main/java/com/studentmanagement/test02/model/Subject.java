package com.studentmanagement.test02.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId;

    @Column(nullable = false, unique = true)
    private String subjectName;

    @Column(name = "subject_code", unique = true)
    private String subjectCode;

    private String description;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Timetable> timetables = new ArrayList<>();

    // Constructors
    public Subject() {}

    public Subject(String subjectName, String subjectCode, String description) {
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.description = description;
    }

    // Getters and setters
    public Long getSubjectId() { return subjectId; }
    public void setSubjectId(Long subjectId) { this.subjectId = subjectId; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public String getSubjectCode() { return subjectCode; }
    public void setSubjectCode(String subjectCode) { this.subjectCode = subjectCode; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Timetable> getTimetables() { return timetables; }
    public void setTimetables(List<Timetable> timetables) { this.timetables = timetables; }
}