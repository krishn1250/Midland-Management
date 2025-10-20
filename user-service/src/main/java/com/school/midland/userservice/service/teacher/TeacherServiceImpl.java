    package com.school.midland.userservice.service.teacher;

    import com.school.midland.commonlib.dtos.TeacherDto;
    import com.school.midland.commonlib.exception.UserException;
    import com.school.midland.userservice.mappers.TeacherMapper;
    import com.school.midland.userservice.models.Teacher;
    import com.school.midland.userservice.repository.TeacherRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.stereotype.Service;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;
    import java.util.UUID;

    @Service
    @RequiredArgsConstructor
    public class TeacherServiceImpl implements TeacherService {

        private final TeacherRepository teacherRepository;

        @Override
        public boolean createTeacher(TeacherDto teacherDto) {
            if (teacherDto == null) {
                throw new UserException("Fill the details", HttpStatus.BAD_REQUEST);
            }

            if (teacherDto.getTeacherCode() != null &&
                    teacherRepository.findByTeacherCode(teacherDto.getTeacherCode()).isPresent()) {
                throw new UserException("Teacher employee ID already exists", HttpStatus.BAD_REQUEST);
            }

            if (teacherDto.getSchoolEmail() != null &&
                    teacherRepository.findBySchoolEmail(teacherDto.getSchoolEmail()).isPresent()) {
                throw new UserException("Teacher email already exists", HttpStatus.BAD_REQUEST);
            }

            final Teacher entity = TeacherMapper.toEntity(teacherDto);
            final Teacher save = teacherRepository.save(entity);

            return save!=null;
        }

        @Override
        public TeacherDto getTeacherById(Long id) {
            if (id == null) throw new UserException("Teacher ID cannot be null", HttpStatus.BAD_REQUEST);
            Optional<Teacher> byId = teacherRepository.findById(id);
            if (byId.isEmpty()) throw new UserException("Teacher not found for ID " + id, HttpStatus.NOT_FOUND);

            return TeacherMapper.toDto(byId.get());
        }

        @Override
        public TeacherDto getTeacherByEmail(String  email) {
            if (email == null) throw new UserException("Teacher UID cannot be null", HttpStatus.BAD_REQUEST);
            Optional<Teacher> byUid = teacherRepository.findBySchoolEmail(email);
            if (byUid.isEmpty()) throw new UserException("Teacher not found for UID " + email, HttpStatus.NOT_FOUND);

            return TeacherMapper.toDto(byUid.get());
        }

        @Override
        public TeacherDto getTeacherByCode(String teacherCode) {
            if (teacherCode == null) throw new UserException("Teacher code cannot be null", HttpStatus.BAD_REQUEST);
            Optional<Teacher> byCode = teacherRepository.findByTeacherCode(teacherCode);
            if (byCode.isEmpty()) throw new UserException("Teacher not found for code " + teacherCode, HttpStatus.NOT_FOUND);

            return TeacherMapper.toDto(byCode.get());
        }

        @Override
        public List<TeacherDto> getAllTeachers() {
            List<Teacher> teachers = teacherRepository.findAll();
            List<TeacherDto> teacherDtos = new ArrayList<>();
            for (Teacher teacher : teachers) {
                teacherDtos.add(TeacherMapper.toDto(teacher));
            }
            return teacherDtos;
        }

        @Override
        public TeacherDto updateTeacher(String teacherCode, TeacherDto teacherDto) {
            if (teacherCode == null || teacherDto == null) {
                throw new UserException("Invalid update request", HttpStatus.BAD_REQUEST);
            }

            Optional<Teacher> optionalTeacher = teacherRepository.findByTeacherCode(teacherCode);
            if (optionalTeacher.isEmpty()) {
                throw new UserException("Teacher not found for code " + teacherCode, HttpStatus.NOT_FOUND);
            }

            Teacher existingTeacher = optionalTeacher.get();

            // Update fields
            existingTeacher.setFirstName(teacherDto.getFirstName());
            existingTeacher.setLastName(teacherDto.getLastName());
            existingTeacher.setDepartment(teacherDto.getDepartment());
            existingTeacher.setDesignation(teacherDto.getDesignation());
            existingTeacher.setSchoolEmail(teacherDto.getSchoolEmail());
            existingTeacher.setPhoneNumber(teacherDto.getPhoneNumber());

            Teacher updated = teacherRepository.save(existingTeacher);
            return TeacherMapper.toDto(updated);
        }

        @Override
        public Boolean deleteTeacher(String email) {
            if (email == null) {
                throw new UserException("Teacher email cannot be null", HttpStatus.BAD_REQUEST);
            }

            Optional<Teacher> optionalTeacher = teacherRepository.findBySchoolEmail(email);

            if (optionalTeacher.isEmpty()) {
                throw new UserException("Teacher not found for email: " + email, HttpStatus.NOT_FOUND);
            }

            teacherRepository.delete(optionalTeacher.get());
            return true;
        }

        @Override
        public List<TeacherDto> findByDepartment(String department) {
            if (department == null || department.isEmpty()) {
                throw new UserException("Department cannot be empty", HttpStatus.BAD_REQUEST);
            }

            Optional<List<Teacher>> teachers = teacherRepository.findByDepartment(department);
            List<TeacherDto> dtos = new ArrayList<>();
            for (Teacher teacher : teachers.get()) {
                dtos.add(TeacherMapper.toDto(teacher));
            }
            return dtos;
        }

        @Override
        public List<TeacherDto> findByDesignation(String designation) {
            if (designation == null || designation.isEmpty()) {
                throw new UserException("Designation cannot be empty", HttpStatus.BAD_REQUEST);
            }

            Optional<List<Teacher>> teachers = teacherRepository.findByDesignation(designation);
            List<TeacherDto> dtos = new ArrayList<>();
            for (Teacher teacher : teachers.get()) {
                dtos.add(TeacherMapper.toDto(teacher));
            }
            return dtos;
        }

        @Override
        public TeacherDto getByUsername(String username) {
            if (username == null) throw new UserException("Teacher UID cannot be null", HttpStatus.BAD_REQUEST);
            Optional<Teacher> byUid = teacherRepository.findByUsername(username);
            if (byUid.isEmpty()) throw new UserException("Teacher not found for UID " + username, HttpStatus.NOT_FOUND);
            return TeacherMapper.toDto(byUid.get());

        }
    }