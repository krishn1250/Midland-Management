package com.school.midland.userservice.service.student;

import com.school.midland.commonlib.exception.UserException;
import com.school.midland.commonlib.dtos.StudentDto;
import com.school.midland.userservice.dto.student.StudentResponseDto;
import com.school.midland.userservice.mappers.StudentMapper;
import com.school.midland.userservice.models.Student;
import com.school.midland.userservice.repository.StudentRepository;
import com.school.midland.userservice.security.JwtTokenValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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
    public StudentResponseDto updateStudent(String email, StudentDto updatedDto) {
        Optional<Student> existingOpt = studentRepository.findBySchoolEmail(email);
        if (existingOpt.isEmpty()) {
            throw  new UserException("student not found to update",HttpStatus.BAD_REQUEST);
        }
        Student student = existingOpt.get();

        if (updatedDto.getFullName() != null && !updatedDto.getFullName().trim().isEmpty()) {
            String[] parts = updatedDto.getFullName().trim().split("\\s+", 2);
            student.setFullName(updatedDto.getFullName());
            student.setFirstName(parts[0]);
            student.setLastName(parts.length > 1 ? parts[1] : "");
        }


        if (updatedDto.getAcademicYear() != null) student.setAcademicYear(updatedDto.getAcademicYear());
        if (updatedDto.getAddress() != null) student.setAddress(updatedDto.getAddress());
        if (updatedDto.getGradeLevel() != null) student.setGradeLevel(updatedDto.getGradeLevel());
        if (updatedDto.getCity() != null) student.setCity(updatedDto.getCity());
        if (updatedDto.getBloodGroup() != null) student.setBloodGroup(updatedDto.getBloodGroup());
        if (updatedDto.getDateOfBirth() != null) student.setDateOfBirth(updatedDto.getDateOfBirth());
        if (updatedDto.getCountry() != null) student.setCountry(updatedDto.getCountry());
        if (updatedDto.getGender() != null) student.setGender(updatedDto.getGender());
//        if (updatedDto.getAdmissionDate() != null) student.setAdmissionDate(updatedDto.getAdmissionDate());
        if (updatedDto.getGuardianRelation() != null) student.setGuardianRelation(updatedDto.getGuardianRelation());
        if (updatedDto.getLanguagePreference() != null) student.setLanguagePreference(updatedDto.getLanguagePreference());
        if (updatedDto.getPhoneNumber() != null) student.setPhoneNumber(updatedDto.getPhoneNumber());
        if (updatedDto.getSchoolEmail() != null) student.setSchoolEmail(updatedDto.getSchoolEmail());
        if (updatedDto.getPersonalEmail()!=null) student.setPersonalEmail(updatedDto.getPersonalEmail());
        StudentResponseDto dto=studentMapper.toReponseDto(studentRepository.save(student));
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
