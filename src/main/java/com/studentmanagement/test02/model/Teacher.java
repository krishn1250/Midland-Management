package com.studentmanagement.test02.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @Column(name = "tuuid")
    private String tuuid; // Same as user.uuid

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    private String phone;

    private String subject; // Primary subject they teach

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Timetable> timetables = new ArrayList<>();

    // Constructors
    public Teacher() {}

    public Teacher(String tuuid, String name, String email, String phone, String subject, LocalDate hireDate) {
        this.tuuid = tuuid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.subject = subject;
        this.hireDate = hireDate;
    }

    // Getters and setters
    public String getTuuid() { return tuuid; }
    public void setTuuid(String tuuid) { this.tuuid = tuuid; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }

    public List<Timetable> getTimetables() { return timetables; }
    public void setTimetables(List<Timetable> timetables) { this.timetables = timetables; }
}