package com.school.midland.authservice.service.user;

import com.school.midland.authservice.dto.user.UserDto;
import com.school.midland.authservice.dto.user.UserUpdateDto;
import com.school.midland.authservice.models.User;

public interface UserService {
    public UserDto getUser(String username);
    public UserDto getByEmail(String email);
    public boolean deleteUser(String schoolEmail);
    UserUpdateDto updateUser(String email, UserUpdateDto updatedUser);
}
