    package com.school.midland.user.service.teacher;

    import com.school.midland.user.dto.PageResponse;
    import com.school.midland.user.dto.teacher.TeacherDto;
    import com.school.midland.user.exception.UserException;
    import com.school.midland.user.mappers.PageMapper;
    import com.school.midland.user.mappers.TeacherMapper;
    import com.school.midland.user.models.Teacher;
    import com.school.midland.user.repository.TeacherRepository;
    import com.school.midland.user.validators.teacher.TeacherDbValidator;
    import com.school.midland.user.validators.teacher.TeacherValidator;
    import lombok.RequiredArgsConstructor;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.PageRequest;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.domain.Sort;
    import org.springframework.http.HttpStatus;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.util.*;

    @Service
    @RequiredArgsConstructor
    public class TeacherServiceImpl implements TeacherService {

        private final TeacherRepository teacherRepository;
        private final TeacherDbValidator teacherDbValidator;
        private final TeacherMapper teacherMapper;
        private final PageMapper pageMapper;

        @Override
        public boolean createTeacher(TeacherDto teacherDto) {
            TeacherValidator.validateTeacherData(teacherDto);

            teacherDbValidator.validateEmail(teacherDto.getSchoolEmail());
            teacherDbValidator.validateTeacherCode(teacherDto.getTeacherCode());

            final Teacher entity = teacherMapper.toEntity(teacherDto);
            final Teacher save = teacherRepository.save(entity);
            if(save==null ){
                throw new UserException("something went wrong while creating teacher", HttpStatus.BAD_REQUEST);
            }
            return teacherMapper.toDto(save)!=null;
        }

        @Override
        public TeacherDto getTeacherById(Long id) {
            if (id == null) throw new UserException("Teacher ID cannot be null", HttpStatus.BAD_REQUEST);
            Optional<Teacher> byId = teacherRepository.findById(id);
            if (byId.isEmpty()) throw new UserException("Teacher not found for ID " + id, HttpStatus.NOT_FOUND);

            return teacherMapper.toDto(byId.get());
        }

        @Override
        public TeacherDto getTeacherByUid(UUID uid) {
            if (uid == null) throw new UserException("Teacher UID cannot be null", HttpStatus.BAD_REQUEST);
            Optional<Teacher> byUid = teacherRepository.findByTeacherUid(uid);
            if (byUid.isEmpty()) throw new UserException("Teacher not found for UID " + uid, HttpStatus.NOT_FOUND);

            return teacherMapper.toDto(byUid.get());
        }

        @Override
        public TeacherDto getTeacherByCode(String teacherCode) {
//            if(teacherCode==null || teacherCode.isEmpty())return

            Teacher teacher = teacherRepository.findByTeacherCode(teacherCode)
                    .orElseThrow(() -> new UserException("Teacher not found for code " + teacherCode, HttpStatus.NOT_FOUND));

            return teacherMapper.toDto(teacher);
        }

        @Override
        public PageResponse<TeacherDto> getAllTeachers(int page, int size, String sortBy) {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
            Page<Teacher> teachersPage = teacherRepository.findAll(pageable);

            if (teachersPage.isEmpty()) {
                throw new UserException("No teachers found", HttpStatus.NOT_FOUND);
            }

            Page<TeacherDto> dtoPage = teachersPage.map(teacherMapper::toDto);
            return pageMapper.toPageResponse(dtoPage);
        }

        @Override
        public TeacherDto updateTeacher(String email, TeacherDto teacherDto) {
            TeacherValidator.validateUpdateTeacherData(teacherDto);
            if(teacherDto==null){
                throw new UserException("Atleast one field is required to update",HttpStatus.BAD_REQUEST);
            }
            Optional<Teacher> optionalTeacher = teacherRepository.findBySchoolEmail(email);
            if (optionalTeacher.isEmpty()) {
                throw new UserException("Teacher not found for code " + email, HttpStatus.NOT_FOUND);
            }

            Teacher existingTeacher = optionalTeacher.get();
            if (teacherDto.getFullName() != null && !teacherDto.getFullName().trim().isEmpty()) {
                String[] parts = teacherDto.getFullName().trim().split("\\s+", 2);
                existingTeacher.setFullName(teacherDto.getFullName());
                existingTeacher.setFirstName(parts[0]);
                existingTeacher.setLastName(parts.length > 1 ? parts[1] : "");
            }


            if (teacherDto.getDepartment() != null) existingTeacher.setDepartment(teacherDto.getDepartment());
            if (teacherDto.getDesignation() != null) existingTeacher.setDesignation(teacherDto.getDesignation());
            if (teacherDto.getSchoolEmail() != null) existingTeacher.setSchoolEmail(teacherDto.getSchoolEmail());
            if (teacherDto.getPhoneNumber() != null) existingTeacher.setPhoneNumber(teacherDto.getPhoneNumber());
            if (teacherDto.getPersonalEmail() != null) existingTeacher.setPersonalEmail(teacherDto.getPersonalEmail());
            if (teacherDto.getProfileImage() != null) existingTeacher.setProfileImage(teacherDto.getProfileImage());
            if (teacherDto.getQualification() != null) existingTeacher.setQualification(teacherDto.getQualification());

            Teacher updated = teacherRepository.save(existingTeacher);
            if(updated==null){
                throw new UserException("something went wrong while updating "+updated,HttpStatus.CONFLICT);
            }
            return teacherMapper.toDto(updated);
        }
        @Transactional
        @Override
        public Boolean deleteTeacher(String schoolEmail) {
            TeacherValidator.validateEmail(schoolEmail);
            Optional<Teacher> optionalTeacher = teacherRepository.findBySchoolEmail(schoolEmail);
            if (optionalTeacher.isEmpty()) {
                throw new UserException("Teacher not found for ID " + schoolEmail, HttpStatus.NOT_FOUND);
            }
            teacherRepository.delete(optionalTeacher.get());
            return true;
        }

        @Override
        public List<TeacherDto> findByDepartment(String department) {
//            TeacherValidator.validateData(department);

            Optional<List<Teacher>> teachers = teacherRepository.findByDepartment(department);
            List<TeacherDto> dtos = new ArrayList<>();
            for (Teacher teacher : teachers.get()) {
                dtos.add(teacherMapper.toDto(teacher));
            }
            return dtos;
        }

        @Override
        public List<TeacherDto> findByDesignation(String designation) {
//            TeacherValidator.validateData(designation);

            List<Teacher> teachers = teacherRepository.findByDesignation(designation)
                    .orElse(Collections.emptyList());

            if (teachers.isEmpty()) {
                throw new UserException("No teachers found for designation " + designation, HttpStatus.NOT_FOUND);
            }

            return teachers.stream().map(teacherMapper::toDto).toList();
        }

        @Override
        public TeacherDto getByUsername(String username) {
//            TeacherValidator.validateData(username);

            Teacher teacher = teacherRepository.findByUsername(username)
                    .orElseThrow(() -> new UserException("Teacher not found for username " + username, HttpStatus.NOT_FOUND));

            return teacherMapper.toDto(teacher);
        }
    }