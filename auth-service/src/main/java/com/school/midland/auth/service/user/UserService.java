package com.school.midland.auth.service.user;

import com.school.midland.auth.dto.response.UserCreationResponse;
import com.school.midland.auth.dto.user.UserDto;
import com.school.midland.auth.dto.user.UserUpdateDto;
import com.school.midland.auth.models.User;

public interface UserService {
    public UserDto getUser(String username);
    public UserDto getByEmail(String email);
    public boolean deleteUser(String schoolEmail);

    UserUpdateDto updateUser(String email, UserUpdateDto updatedUser);
}
