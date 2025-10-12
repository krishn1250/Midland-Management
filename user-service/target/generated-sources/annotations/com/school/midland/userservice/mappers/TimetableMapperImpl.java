package com.school.midland.userservice.mappers;

import com.school.midland.commonlib.dtos.TimetableDto;
import com.school.midland.userservice.models.Timetable;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-12T18:00:30+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Microsoft)"
)
@Component
public class TimetableMapperImpl implements TimetableMapper {

    @Override
    public Timetable toEntity(TimetableDto dto) {
        if ( dto == null ) {
            return null;
        }

        Timetable.TimetableBuilder timetable = Timetable.builder();

        timetable.academicYear( dto.getAcademicYear() );
        timetable.id( dto.getId() );
        timetable.gradeLevel( dto.getGradeLevel() );
        timetable.section( dto.getSection() );
        timetable.dayOfWeek( dto.getDayOfWeek() );
        timetable.periodNumber( dto.getPeriodNumber() );
        timetable.startTime( dto.getStartTime() );
        timetable.endTime( dto.getEndTime() );
        timetable.subjectCode( dto.getSubjectCode() );
        timetable.subjectName( dto.getSubjectName() );
        timetable.teacherCode( dto.getTeacherCode() );
        timetable.roomNumber( dto.getRoomNumber() );
        timetable.createdAt( dto.getCreatedAt() );
        timetable.updatedAt( dto.getUpdatedAt() );

        return timetable.build();
    }

    @Override
    public TimetableDto toDto(Timetable entity) {
        if ( entity == null ) {
            return null;
        }

        TimetableDto timetableDto = new TimetableDto();

        timetableDto.setId( entity.getId() );
        timetableDto.setGradeLevel( entity.getGradeLevel() );
        timetableDto.setSection( entity.getSection() );
        timetableDto.setDayOfWeek( entity.getDayOfWeek() );
        timetableDto.setPeriodNumber( entity.getPeriodNumber() );
        timetableDto.setStartTime( entity.getStartTime() );
        timetableDto.setEndTime( entity.getEndTime() );
        timetableDto.setSubjectCode( entity.getSubjectCode() );
        timetableDto.setSubjectName( entity.getSubjectName() );
        timetableDto.setTeacherCode( entity.getTeacherCode() );
        timetableDto.setRoomNumber( entity.getRoomNumber() );
        timetableDto.setAcademicYear( entity.getAcademicYear() );
        timetableDto.setCreatedAt( entity.getCreatedAt() );
        timetableDto.setUpdatedAt( entity.getUpdatedAt() );

        return timetableDto;
    }
}
