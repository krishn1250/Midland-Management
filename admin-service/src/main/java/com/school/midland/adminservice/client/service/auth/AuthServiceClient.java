package com.school.midland.adminservice.client.service.auth;

import com.school.midland.adminservice.client.dtos.UserCreationRequest;
import com.school.midland.adminservice.client.dtos.UserCreationResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthServiceClient {
    UserCreationResponse createUser(@RequestBody UserCreationRequest userCreationRequest);
}
