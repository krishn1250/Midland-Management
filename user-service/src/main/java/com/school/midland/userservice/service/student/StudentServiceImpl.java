package com.school.midland.userservice.service.student;

import com.school.midland.commonlib.exception.UserException;
import com.school.midland.commonlib.dtos.StudentDto;
import com.school.midland.userservice.mappers.StudentMapper;
import com.school.midland.userservice.models.Student;
import com.school.midland.userservice.repository.StudentRepository;
import com.school.midland.userservice.security.JwtTokenValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private  final StudentMapper studentMapper;
    private final JwtTokenValidator jwtTokenValidator;


    @Override
    public boolean createStudent(StudentDto studentDto) {
        if (studentDto == null ) {
            throw new UserException("fill the details",HttpStatus.BAD_REQUEST);
        }
        if(studentDto.getAdmissionNumber() !=null && studentRepository.findByAdmissionNumber(studentDto.getAdmissionNumber()).isPresent()) {
            throw new UserException("student admission number already exists", HttpStatus.BAD_REQUEST);
        }
        if(studentDto.getSchoolEmail()!=null && studentRepository.findBySchoolEmail(studentDto.getSchoolEmail()).isPresent()){
            throw  new UserException("student email already exists", HttpStatus.BAD_REQUEST);
        }
        studentDto.setCreatedAt(LocalDateTime.now());
        final Student model = studentMapper.toModel(studentDto);
        final Student student =studentRepository.save(model);
        final StudentDto dto = studentMapper.toDto(student);
        System.out.println(studentDto);
        return dto!=null;
    }

    @Override
    public StudentDto getStudentById(Long id) {
       if(id==null) throw new  UserException("provide id",HttpStatus.BAD_REQUEST);
      Optional<Student> student= studentRepository.findByStudentId(id);
      if(student.get()==null)throw new UserException("user not found with current details",HttpStatus.BAD_REQUEST);
        StudentDto dto = studentMapper.toDto(student.get());
        return  dto;
    }

    @Override
    public StudentDto getStudentByUid(UUID uid) {
        if(uid==null) throw new  UserException("provide uid",HttpStatus.BAD_REQUEST);
        Optional<Student> student= studentRepository.findByStudentUid(uid);
        if(student.get()==null)throw new UserException("student not found with current details",HttpStatus.BAD_REQUEST);
        StudentDto dto = studentMapper.toDto(student.get());
        return  dto;
    }

    @Override
    public StudentDto getStudentByAdmissionNumber(String admissionNumber) {
        if(admissionNumber.isEmpty() || admissionNumber.isBlank()) throw new  UserException("provide valid admission number",HttpStatus.BAD_REQUEST);
        Optional<Student> student= studentRepository.findByAdmissionNumber(admissionNumber);
        if(student.get()==null)throw new UserException("student not found with admission_id "+admissionNumber,HttpStatus.BAD_REQUEST);
        StudentDto dto = studentMapper.toDto(student.get());
        return  dto;
    }

    @Override
    public StudentDto updateStudent(String admissionNumber, StudentDto updatedDto) {
        Optional<Student> existingOpt = studentRepository.findByAdmissionNumber(admissionNumber);
        if (existingOpt.isEmpty()) {
            throw  new UserException("student not found to update",HttpStatus.BAD_REQUEST);
        }
        Student student = existingOpt.get();
        student.setFullName(updatedDto.getFullName());
        student.setAcademicYear(updatedDto.getAcademicYear());
        student.setAddress(updatedDto.getAddress());
        student.setGradeLevel(updatedDto.getGradeLevel());
        student.setCity(updatedDto.getCity());
        student.setBloodGroup(updatedDto.getBloodGroup());
        student.setDateOfBirth(updatedDto.getDateOfBirth());
        student.setCountry(updatedDto.getCountry());
        student.setGender(updatedDto.getGender());
        student.setAdmissionDate(updatedDto.getAdmissionDate());
        student.setGuardianRelation(updatedDto.getGuardianRelation());
        student.setLanguagePreference(updatedDto.getLanguagePreference());
        student.setPhoneNumber(updatedDto.getPhoneNumber());
        student.setSchoolEmail(updatedDto.getSchoolEmail());
        student.setUpdatedAt(LocalDateTime.now());
        StudentDto dto=studentMapper.toDto(studentRepository.save(student));
        return dto ;
    }

    @Override
    public boolean deleteStudentBySchoolEmail(String email) {
        Student student = studentRepository.findBySchoolEmail(email)
                .orElseThrow(() -> new UserException("Student not found to delete", HttpStatus.BAD_REQUEST));

        studentRepository.delete(student);
        return true;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<StudentDto> dto=new ArrayList<>();
        List<Student> students = studentRepository.findAll();
        for(Student student:students)dto.add(studentMapper.toDto(student));
        return dto;
    }

    @Override
    public List<StudentDto> getStudentsByGradeLevel(String gradeLevel) {
        List<StudentDto> dto=new ArrayList<>();
        List<Student> students = studentRepository.findByGradeLevel(gradeLevel);
        for(Student student:students)dto.add(studentMapper.toDto(student));
        return dto;
    }

    @Override
    public List<StudentDto> getStudentsByAcademicYear(String academicYear) {
        List<StudentDto> dto=new ArrayList<>();
        List<Student> students = studentRepository.findByAcademicYear(academicYear);
        for(Student student:students)dto.add(studentMapper.toDto(student));
        return dto;
    }

    @Override
    public List<StudentDto> getStudentsGradeAndSection(String grade, String section) {
        List<StudentDto> dto=new ArrayList<>();
        List<Student> students=  studentRepository.findByGradeLevelAndSection(grade, section);
        for(Student student:students)dto.add(studentMapper.toDto(student));
        return dto;
    }

    @Override
    public StudentDto getCurrentStudent(String token) {
        System.out.println(token);
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " (including space)
        } else {
            throw new RuntimeException("Invalid or missing token");
        }

       String username= jwtTokenValidator.extractUsername(token);
        System.out.println(username);
       String code=jwtTokenValidator.extractAssociateIdentifier(token);
        System.out.println(code);
        Student student=studentRepository.findByUsernameAndAdmissionNumber(username,code);
        System.out.println(student);
      StudentDto dto= studentMapper.toDto(student);
        System.out.println(dto);
        return dto;
    }
}
