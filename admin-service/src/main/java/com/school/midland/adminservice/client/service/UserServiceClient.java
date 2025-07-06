package com.school.midland.adminservice.client.service;

import com.school.midland.adminservice.client.dto.UserCreationRequest;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

//@FeignClient(name = "user-service", url = "${user-service.base-url}")
public interface UserServiceClient {
    //    @PostMapping("/api/users/create")
    UUID createUser(@RequestBody UserCreationRequest userCreationRequest);
    void updateAssociatedIdentifier(UUID userUid, String actualAssociatedId);
}