package com.school.midland.auth.service.user;

import com.school.midland.auth.dto.user.UserDto;
import com.school.midland.auth.dto.user.UserUpdateDto;
import com.school.midland.auth.exception.AuthException;
import com.school.midland.auth.mapper.UserMapper;
import com.school.midland.auth.models.User;
import com.school.midland.auth.repository.UserRepository;
import com.school.midland.auth.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{
private final UserRepository userRepository;
private final UserMapper userMapper;


    @Override
    public UserDto getUser(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            throw new AuthException("User not found: " + username, HttpStatus.NOT_FOUND);
        }

        UserDto user = userMapper.userToDto(userOpt.get());
        System.out.println(user);
        return user;
    }


    @Override
    public UserDto getByEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new AuthException("User not found with email: " + email, HttpStatus.NOT_FOUND);
        }
        return userMapper.userToDto(userOpt.get());
    }


    @Override
    public boolean deleteUser(String schoolEmail) {
        return userRepository.findByEmail(schoolEmail)
                .map(user -> {
                    userRepository.delete(user);
                    return true;
                })
                .orElseThrow(() -> new AuthException("user not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public UserUpdateDto updateUser(String email, UserUpdateDto updatedUser) {
        UserValidator.validateUserUpdate(updatedUser);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthException("User not found with email: " + email, HttpStatus.NOT_FOUND));

        if (updatedUser.getFullName() != null)
            user.setFullName(updatedUser.getFullName());
        if (updatedUser.getPhoneNumber() != null)
            user.setPhoneNumber(updatedUser.getPhoneNumber());
        if (updatedUser.getPassword() != null)
            user.setPassword(updatedUser.getPassword());
        if (updatedUser.getEmail() != null)
            user.setEmail(updatedUser.getEmail());

        userRepository.save(user);

        return userMapper.userToUpdateDto(user);
    }


}
