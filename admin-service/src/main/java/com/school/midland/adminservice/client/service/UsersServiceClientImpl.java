package com.school.midland.adminservice.client.service;



import com.school.midland.adminservice.client.dto.UserCreationRequest;
import com.school.midland.adminservice.client.dto.UserCreationResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersServiceClientImpl implements  UserServiceClient{
    private final RestTemplate restTemplate;

    @Value("${user-service.base-url}")
    private String userServiceBaseUrl;

    @Override
    public UUID createUser(UserCreationRequest userReq) {

        Map<String, Object> payload = new HashMap<>();
        payload.put("username",userReq.getUsername() );
        payload.put("password",userReq.getPassword());
        payload.put("email",userReq.getEmail());
        payload.put("phoneNumber",userReq.getPhoneNumber());
        payload.put("role", userReq.getRole());
        payload.put("associatedIdentifier",""+ userReq.getAssociatedIdentifier());

        return restTemplate.postForObject(userServiceBaseUrl + "api/auth/signup", payload,  UserCreationResponse.class).getUserUid();

    }

    @Override
    public void updateAssociatedIdentifier(UUID userUid, String actualAssociatedId) {

    }
}
