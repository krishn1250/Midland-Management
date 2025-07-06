package com.studentmanagement.test02.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @Column(name = "suuid")
    private String suuid; // Same as user.uuid

    @Column(nullable = false)
    private String name;

    @Column(name = "roll_number", unique = true)
    private String rollNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    private String email;
    private String phone;
    private String address;

    @Column(name = "admission_date")
    private LocalDate admissionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", referencedColumnName = "sectionId")
    private Section section;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Attendance> attendanceRecords = new ArrayList<>();

    // Constructors
    public Student() {}

    public Student(String suuid, String name, String rollNumber, LocalDate dateOfBirth,
                   String email, String phone, String address, LocalDate admissionDate) {
        this.suuid = suuid;
        this.name = name;
        this.rollNumber = rollNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.admissionDate = admissionDate;
    }

    // Getters and setters
    public String getSuuid() { return suuid; }
    public void setSuuid(String suuid) { this.suuid = suuid; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public LocalDate getAdmissionDate() { return admissionDate; }
    public void setAdmissionDate(LocalDate admissionDate) { this.admissionDate = admissionDate; }

    public Section getSection() { return section; }
    public void setSection(Section section) { this.section = section; }

    public List<Attendance> getAttendanceRecords() { return attendanceRecords; }
    public void setAttendanceRecords(List<Attendance> attendanceRecords) { this.attendanceRecords = attendanceRecords; }
}