package com.school.midland.userservice.service;

import com.school.midland.userservice.model.Teacher;
import com.school.midland.userservice.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public void createTeacher(String name, String subjectId, String sectionId) {
        // Implementation for creating a teacher
        // This method should save the teacher to the repository
    }
    public Teacher getTeacherById(String teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));

        return teacher;
    }
}
