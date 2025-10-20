package com.school.midland.authservice.service.user;

import com.school.midland.authservice.models.User;

public interface UserService {
    public User getUser(String username);
    public User getByEmail(String email);
    public boolean deleteUser(String schoolEmail);
}
