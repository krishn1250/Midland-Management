package com.school.midland.adminservice.client.service.auth;

import com.school.midland.adminservice.client.dtos.UserCreationRequest;
import com.school.midland.adminservice.client.dtos.UserCreationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceClientImpl implements AuthServiceClient {

    private final RestTemplate restTemplate;

    @Value("${auth-service.base-url}")
    private String authServiceBaseUrl;

    @Override
    public  UserCreationResponse createUser(UserCreationRequest userCreationRequest) {
        System.out.println(userCreationRequest);
        Map<String, Object> payload = new HashMap<>();
        payload.put("username",userCreationRequest.getUsername() );
        payload.put("password",userCreationRequest.getPassword());
        payload.put("email",userCreationRequest.getEmail());
        payload.put("phoneNumber",userCreationRequest.getPhoneNumber());
        payload.put("role", userCreationRequest.getRole());
        payload.put("associatedIdentifier",""+ userCreationRequest.getAssociatedIdentifier());
        return restTemplate.postForObject(authServiceBaseUrl + "auth/register", payload,  UserCreationResponse.class);
    }
}
