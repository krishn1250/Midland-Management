package com.school.midland.userservice.mappers;

import com.school.midland.commonlib.dtos.StudentDto;
import com.school.midland.userservice.models.Student;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StudentMapper {

    public  Student toModel(StudentDto studentDto){
        if (studentDto == null) return null;

        return Student.builder()
                .studentUid(studentDto.getStudentUid()!=null ? studentDto.getStudentUid() : UUID.randomUUID())
                .username(studentDto.getUsername())
                .admissionNumber(studentDto.getAdmissionNumber())
                .rollNo(studentDto.getRollNo())
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .fullName(studentDto.getFullName())
                .gender(studentDto.getGender())
                .dateOfBirth(studentDto.getDateOfBirth())
                .bloodGroup(studentDto.getBloodGroup())
                .nationality(studentDto.getNationality())
                .motherTongue(studentDto.getMotherTongue())
                .languagePreference(studentDto.getLanguagePreference())
                .gradeLevel(studentDto.getGradeLevel())
                .section(studentDto.getSection())
                .academicYear(studentDto.getAcademicYear())
                .admissionDate(studentDto.getAdmissionDate())
                .status(studentDto.getStatus())
                .profileImage(studentDto.getProfileImage())
                .address(studentDto.getAddress())
                .city(studentDto.getCity())
                .state(studentDto.getState())
                .country(studentDto.getCountry())
                .pinCode(studentDto.getPinCode())
                .phoneNumber(studentDto.getPhoneNumber())
                .schoolEmail(studentDto.getSchoolEmail())
                .personalEmail(studentDto.getPersonalEmail())
                .guardianName(studentDto.getGuardianName())
                .guardianRelation(studentDto.getGuardianRelation())
                .guardianContact(studentDto.getGuardianContact())
                .createdAt(studentDto.getCreatedAt())
                .updatedAt(studentDto.getUpdatedAt())
                .build();
    }

    public StudentDto toDto(Student student) {
        if (student == null) return null;

        return StudentDto.builder()
                .studentUid(student.getStudentUid())
                .username(student.getUsername())
                .admissionNumber(student.getAdmissionNumber())
                .rollNo(student.getRollNo())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .fullName(student.getFullName())
                .gender(student.getGender())
                .dateOfBirth(student.getDateOfBirth())
                .bloodGroup(student.getBloodGroup())
                .nationality(student.getNationality())
                .motherTongue(student.getMotherTongue())
                .languagePreference(student.getLanguagePreference())
                .gradeLevel(student.getGradeLevel())
                .section(student.getSection())
                .academicYear(student.getAcademicYear())
                .admissionDate(student.getAdmissionDate())
                .status(student.getStatus())
                .profileImage(student.getProfileImage())
                .address(student.getAddress())
                .city(student.getCity())
                .state(student.getState())
                .country(student.getCountry())
                .pinCode(student.getPinCode())
                .phoneNumber(student.getPhoneNumber())
                .schoolEmail(student.getSchoolEmail())
                .personalEmail(student.getPersonalEmail())
                .guardianName(student.getGuardianName())
                .guardianRelation(student.getGuardianRelation())
                .guardianContact(student.getGuardianContact())
                .createdAt(student.getCreatedAt())
                .updatedAt(student.getUpdatedAt())
                .build();
    }
}
