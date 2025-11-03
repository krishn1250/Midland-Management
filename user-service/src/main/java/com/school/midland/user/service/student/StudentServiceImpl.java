package com.school.midland.user.service.student;

import com.school.midland.user.dto.PageResponse;
import com.school.midland.user.dto.student.StudentDto;
import com.school.midland.user.dto.student.StudentResponseDto;
import com.school.midland.user.exception.UserException;
import com.school.midland.user.mappers.PageMapper;
import com.school.midland.user.mappers.StudentMapper;
import com.school.midland.user.models.Student;
import com.school.midland.user.repository.StudentRepository;
import com.school.midland.user.security.JwtTokenValidator;
import com.school.midland.user.validators.student.StudentDbValidator;
import com.school.midland.user.validators.student.StudentValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final PageMapper pageMapper;
    private  final StudentDbValidator studentDbValidator;


    @Override
    public boolean createStudent(StudentDto studentDto) {

        StudentValidator.validateStudentData(studentDto);
        studentDbValidator.validateEmail(studentDto.getSchoolEmail());
       studentDbValidator.validateAdmissionNumber(studentDto.getAdmissionNumber());

        studentDto.setCreatedAt(LocalDateTime.now());
        final Student model = studentMapper.toModel(studentDto);
        final Student student =studentRepository.save(model);
        if(student==null){
            throw new UserException("something went wrong while creating student ",HttpStatus.BAD_GATEWAY);
        }
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
        StudentValidator.validateAdmissionNumber(admissionNumber);

        Optional<Student> student= studentRepository.findByAdmissionNumber(admissionNumber.toUpperCase());

        if(student.isEmpty())throw new UserException("student not found with admission_id "+admissionNumber,HttpStatus.BAD_REQUEST);

        StudentDto dto = studentMapper.toDto(student.get());
        return  dto;
    }

    @Override
    public StudentDto getByStudentEmail(String email) {
        StudentValidator.validateEmail(email);

        Student student = studentRepository.findBySchoolEmail(email)
                .orElseThrow(() -> new UserException("Student not found with email: " + email, HttpStatus.NOT_FOUND));


        return studentMapper.toDto(student);
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

    @Transactional
    @Override
    public boolean deleteStudentByEmail(String email) {
        StudentValidator.validateEmail(email);
        Optional<Student> studentOpt = studentRepository.findBySchoolEmail(email);
        if (studentOpt.isEmpty()) {
            throw  new UserException("student not found to delete",HttpStatus.BAD_REQUEST);
        }
        studentRepository.delete(studentOpt.get());
        return true;
    }

    @Override
    public PageResponse<StudentDto> getAllStudents(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Student> studentsPage = studentRepository.findAll(pageable);
        if(studentsPage.isEmpty()){
            throw  new UserException("No students found ",HttpStatus.NOT_FOUND);
        }
        Page<StudentDto> dtoPage = studentsPage.map(studentMapper::toDto);
        return pageMapper.toPageResponse(dtoPage);
    }

    @Override
    public PageResponse<StudentDto> getStudentsByGradeLevel(int page, int size, String sortBy,String gradeLevel) {
        StudentValidator.ValidateData(gradeLevel);
        Pageable pageable=PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Student> studentsPage = studentRepository.findByGradeLevel(gradeLevel,pageable);
        if(studentsPage.isEmpty()){
            throw  new UserException("students not found with given grade level ",HttpStatus.NOT_FOUND);
        }
        Page<StudentDto> dtoPage = studentsPage.map(studentMapper::toDto);

        return pageMapper.toPageResponse(dtoPage);
    }

    @Override
    public PageResponse<StudentDto> getStudentsByAcademicYear(int page, int size, String sortBy,String academicYear) {
        StudentValidator.ValidateData(academicYear);

        Pageable pageable=PageRequest.of(page,size,Sort.by(sortBy).ascending());
        Page<Student> studentPage=studentRepository.findByAcademicYear(academicYear,pageable);
        if(studentPage.isEmpty()){
            throw  new UserException("students not found with given academic year ",HttpStatus.NOT_FOUND);
        }
        Page<StudentDto> dtoPage = studentPage.map(studentMapper::toDto);
        return pageMapper.toPageResponse(dtoPage);
    }

    @Override
    public List<StudentDto> getStudentsGradeAndSection(String grade, String section) {
        StudentValidator.ValidateData(grade);
        StudentValidator.ValidateData(section);

        List<Student> students=  studentRepository.findByGradeLevelAndSection(grade, section);
        if(students.isEmpty()){
            throw new UserException("No users for found for given grade and section ",HttpStatus.NOT_FOUND);
        }

        return students.stream().map(studentMapper::toDto).toList();
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
