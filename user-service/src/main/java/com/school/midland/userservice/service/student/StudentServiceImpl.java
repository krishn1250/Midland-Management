package com.school.midland.userservice.service.student;

import com.school.midland.commonlib.exception.UserException;
import com.school.midland.commonlib.dtos.StudentDto;
import com.school.midland.userservice.mappers.StudentMapper;
import com.school.midland.userservice.models.Student;
import com.school.midland.userservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private  final StudentMapper studentMapper;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        if (studentDto == null ) {
            throw new UserException("fill the details",HttpStatus.BAD_REQUEST);
        }
        if(studentDto.getAdmissionNumber() !=null && studentRepository.findByAdmissionNumber(studentDto.getAdmissionNumber()).isPresent()) {
            throw new UserException("student admission number already exists", HttpStatus.BAD_REQUEST);
        }
        if(studentDto.getSchoolEmail()!=null && studentRepository.findBySchoolEmail(studentDto.getSchoolEmail()).isPresent()){
            throw  new UserException("student email already exists", HttpStatus.BAD_REQUEST);
        }

        final Student model = studentMapper.toModel(studentDto);

        final Student student =studentRepository.save(model);

        final StudentDto dto = studentMapper.toDto(student);
        System.out.println(studentDto);
        return dto;
    }

//    @Override
//    public StudentDto getStudentById(Long id) {
//        return studentRepository.findById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @Override
//    public StudentDto getStudentByUid(UUID uid) {
//        return studentRepository.findByStudentUid(uid)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @Override
//    public StudentDto getStudentByAdmissionNumber(String admissionNumber) {
//        return studentRepository.findByAdmissionNumber(admissionNumber)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @Override
//    public StudentDto updateStudent(String admissionNumber, StudentDto updatedDto) {
//        Optional<Student> existingOpt = studentRepository.findByAdmissionNumber(admissionNumber);
//        if (existingOpt.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        Student student = existingOpt.get();
//        student.setFullName(updatedDto.getFullName());
//        student.setAcademicYear(updatedDto.getAcademicYear());
//        student.setAddress(updatedDto.getAddress());
//        student.setGradeLevel(updatedDto.getGradeLevel());
//        student.setCity(updatedDto.getCity());
//        student.setBloodGroup(updatedDto.getBloodGroup());
//        student.setDateOfBirth(updatedDto.getDateOfBirth());
//        student.setCountry(updatedDto.getCountry());
//        student.setGender(updatedDto.getGender());
//        student.setAdmissionDate(updatedDto.getAdmissionDate());
//        student.setGuardianRelation(updatedDto.getGuardianRelation());
//        student.setLanguagePreference(updatedDto.getLanguagePreference());
//        student.setPhoneNumber(updatedDto.getPhoneNumber());
//        student.setSchoolEmail(updatedDto.getSchoolEmail());
//
//        studentRepository.save(student);
//        return ResponseEntity.ok(student);
//    }
//
//    @Override
//    public StudentDto deleteStudentById(Long id) {
//        Optional<Student> studentOpt = studentRepository.findById(id);
//        if (studentOpt.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        studentRepository.deleteById(id);
//        return ResponseEntity.ok("Student deleted successfully");
//    }
//
//    @Override
//    public StudentDto getAllStudents() {
//        List<Student> students = studentRepository.findAll();
//        return ResponseEntity.ok(students);
//    }
//
//    @Override
//    public StudentDto getStudentsByGradeLevel(String gradeLevel) {
//        List<Student> students = studentRepository.findByGradeLevel(gradeLevel);
//        return ResponseEntity.ok(students);
//    }
//
//    @Override
//    public StudentDto getStudentsByAcademicYear(String academicYear) {
//        List<Student> students = studentRepository.findByAcademicYear(academicYear);
//        return ResponseEntity.ok(students);
//    }
//
//    @Override
//    public Student findByGradeAndSection(String grade, String section) {
//        List<Student> students=  studentRepository.findByGradeLevelAndSection(grade, section);
//        return ResponseEntity.ok(students);
//    }
}
