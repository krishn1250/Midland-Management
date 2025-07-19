package com.school.midland.adminservice.client.service.teacher;


import com.school.midland.commonlib.dtos.TeacherDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface TeacherServiceClient {
    TeacherDto createUserRest(@RequestBody TeacherDto userCreationRequest);
}
