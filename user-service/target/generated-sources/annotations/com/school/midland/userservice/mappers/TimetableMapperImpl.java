package com.school.midland.userservice.mappers;

import com.school.midland.commonlib.dtos.TimetableDto;
import com.school.midland.userservice.models.Timetable;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-12T18:22:19+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Microsoft)"
)
@Component
public class TimetableMapperImpl implements TimetableMapper {

    @Override
    public Timetable toEntity(TimetableDto dto) {
        if ( dto == null ) {
            return null;
        }

        Timetable timetable = new Timetable();

        return timetable;
    }

    @Override
    public TimetableDto toDto(Timetable entity) {
        if ( entity == null ) {
            return null;
        }

        TimetableDto timetableDto = new TimetableDto();

        return timetableDto;
    }
}
