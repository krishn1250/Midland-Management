package com.school.midland.authservice.service.user;

import com.school.midland.authservice.dto.user.UserDto;
import com.school.midland.authservice.models.User;

public interface UserService {
    public UserDto getUser(String username);
    public UserDto getByEmail(String email);
    public boolean deleteUser(String schoolEmail);
    UserDto updateUser(String email, User updatedUser);
}
