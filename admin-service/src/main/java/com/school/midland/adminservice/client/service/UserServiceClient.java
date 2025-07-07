package com.school.midland.adminservice.client.service;

import com.school.midland.adminservice.client.dto.UserCreationRequest;
import com.school.midland.adminservice.client.dto.UserCreationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;


//@FeignClient(name = "user-service", url = "${user-service.base-url}")
public interface UserServiceClient {
//    @PostMapping("/midland/auth/signup")
    UserCreationResponse createUser(@RequestBody UserCreationRequest userCreationRequest);
//    void updateAssociatedIdentifier(UUID userUid, String actualAssociatedId);
}