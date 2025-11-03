package com.school.midland.auth.mapper;


import com.school.midland.auth.dto.user.UserDto;
import com.school.midland.auth.dto.user.UserUpdateDto;
import com.school.midland.auth.models.User;
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
//                .userUid(user.getUserUid())
                .fullName(user.getFullName())
                .build();
    }

    public UserUpdateDto userToUpdateDto(User user){
        return UserUpdateDto.builder()
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .role(user.getRole())
                .fullName(user.getFullName())

                .build();
    }
}
