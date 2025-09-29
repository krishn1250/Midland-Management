    package com.school.midland.userservice.repository;


    import com.school.midland.commonlib.dtos.SyllabusDto;
    import com.school.midland.userservice.models.Syllabus;
    import org.springframework.data.jpa.repository.JpaRepository;

    import java.util.List;

    public interface SyllabusRepository extends JpaRepository<Syllabus, Long> {
        List<Syllabus> findByGradeLevelAndSubjectCode(String gradeLevel, String subjectCode);

        List<Syllabus> getSyllabusByGradeLevelAndSubjectCode(String gradeLevel, String subjectCode);
        List<Syllabus> getSyllabusByGradeLevelAndSubjectName(String gradeLevel,String subjectName);

        List<Syllabus> getSyllabusByGradeLevel(String gradeALevel);
    }
