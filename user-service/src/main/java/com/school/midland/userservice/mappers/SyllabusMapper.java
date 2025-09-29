    package com.school.midland.userservice.mappers;

    import com.school.midland.commonlib.dtos.SyllabusDto;
    import com.school.midland.userservice.models.Syllabus;

    import java.time.LocalDateTime;

    public class SyllabusMapper {

        public static SyllabusDto mapToDTO(Syllabus syllabus) {
            return SyllabusDto.builder()
                    .id(syllabus.getId())
                    .subjectCode(syllabus.getSubjectCode())
                    .subjectName(syllabus.getSubjectName())
                    .gradeLevel(syllabus.getGradeLevel())
                    .curriculum(syllabus.getCurriculum())
                    .topicTitle(syllabus.getTopicTitle())
                    .description(syllabus.getDescription())
                    .uploadedByTeacherCode(syllabus.getUploadedByTeacherCode())
                    .createdAt(LocalDateTime.now())
                    .build();
        }

        public static Syllabus mapToEntity(SyllabusDto dto) {
            return Syllabus.builder()
                    .id(dto.getId())
                    .subjectCode(dto.getSubjectCode())
                    .subjectName(dto.getSubjectName())
                    .gradeLevel(dto.getGradeLevel())
                    .curriculum(dto.getCurriculum())
                    .topicTitle(dto.getTopicTitle())
                    .description(dto.getDescription())
                    .uploadedByTeacherCode(dto.getUploadedByTeacherCode())
                    .createdAt(LocalDateTime.now())
                    .build();
        }

    }
