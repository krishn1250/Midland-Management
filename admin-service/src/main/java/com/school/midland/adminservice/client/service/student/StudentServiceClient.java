package com.school.midland.adminservice.client.service.student;

import com.school.midland.adminservice.client.dtos.UserCreationRequest;
import com.school.midland.adminservice.client.dtos.UserCreationResponse;

import com.school.midland.commonlib.dtos.StudentDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface StudentServiceClient {
    StudentDto createUserRest(@RequestBody StudentDto userCreationRequest);
}
