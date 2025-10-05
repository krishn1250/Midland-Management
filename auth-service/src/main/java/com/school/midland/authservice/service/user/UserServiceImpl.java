package com.school.midland.authservice.service.user;

import com.school.midland.authservice.models.User;
import com.school.midland.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{
private final UserRepository userRepository;


    @Override
    public User getUser(String username) {


        return userRepository.findByUsername(username).orElse(null);
    }
}
