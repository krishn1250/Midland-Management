package com.school.midland.adminservice.client.service.auth;

import com.school.midland.adminservice.client.dtos.UserCreationRequest;
import com.school.midland.adminservice.client.dtos.UserCreationResponse;
import com.school.midland.adminservice.client.dtos.UserDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthServiceClient {
    UserCreationResponse createUser(@RequestBody UserCreationRequest userCreationRequest);
UserDto getbyuserName(String username);
    public UserDto updateUser(String email,UserDto updatedUser);
    public UserDto getByEmail(String email);
    public boolean deleteUser(String schoolEmail,String authHeader);

}
