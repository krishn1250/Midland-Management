package com.school.midland.authservice.service.user;

import com.school.midland.authservice.models.User;
import com.school.midland.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{
private final UserRepository userRepository;


    @Override
    public User getUser(String username) {

        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User getByEmail(String email) {

        return userRepository.findByEmail(email).get();
    }

    @Override
    public boolean deleteUser(String schoolEmail) {
        return userRepository.findByEmail(schoolEmail).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }

}
