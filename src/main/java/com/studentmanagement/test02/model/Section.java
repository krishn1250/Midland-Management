package com.studentmanagement.test02.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sections")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sectionId") // Explicitly specify column name
    private Long sectionId;

    @Column(nullable = false)
    private String grade;

    @Column(nullable = false)
    private String sectionName;

    @Column(nullable = false)
    private String academicYear;

    @Column(name = "class_teacher_uuid")
    private String classTeacherUuid;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Timetable> timetables = new ArrayList<>();

    // Constructors
    public Section() {}

    public Section(String grade, String sectionName, String academicYear, String classTeacherUuid) {
        this.grade = grade;
        this.sectionName = sectionName;
        this.academicYear = academicYear;
        this.classTeacherUuid = classTeacherUuid;
    }

    // Getters and setters
    public Long getSectionId() { return sectionId; }
    public void setSectionId(Long sectionId) { this.sectionId = sectionId; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getSectionName() { return sectionName; }
    public void setSectionName(String sectionName) { this.sectionName = sectionName; }

    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }

    public String getClassTeacherUuid() { return classTeacherUuid; }
    public void setClassTeacherUuid(String classTeacherUuid) { this.classTeacherUuid = classTeacherUuid; }

    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }

    public List<Timetable> getTimetables() { return timetables; }
    public void setTimetables(List<Timetable> timetables) { this.timetables = timetables; }
}