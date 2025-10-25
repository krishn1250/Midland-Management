package com.school.midland.authservice.mapper;

import com.school.midland.authservice.dto.user.UserDto;
import com.school.midland.authservice.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public User DtotoUser(UserDto user){
        return User.builder()
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .role(user.getRole())
                .fullName(user.getFullName())
                .build();

    }
    public UserDto userToDto(User user){
        return UserDto.builder()
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .role(user.getRole())
                .fullName(user.getFullName())
                .build();
    }
}
