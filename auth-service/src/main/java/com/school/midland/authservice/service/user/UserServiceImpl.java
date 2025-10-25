package com.school.midland.authservice.service.user;

import com.school.midland.authservice.dto.user.UserDto;
import com.school.midland.authservice.exception.AuthException;
import com.school.midland.authservice.mapper.UserMapper;
import com.school.midland.authservice.models.User;
import com.school.midland.authservice.repository.UserRepository;
import com.school.midland.commonlib.exception.UserException;
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
        UserDto user=userMapper.userToDto(userRepository.findByUsername(username).get());
        if (user==null){
            throw new AuthException("user not found", HttpStatus.NOT_FOUND);

        }
        return user;
    }

    @Override
    public UserDto getByEmail(String email) {
        UserDto user=userMapper.userToDto(userRepository.findByEmail(email).get());
        if (user==null){
            throw new AuthException("user not found", HttpStatus.NOT_FOUND);

        }
        return user;
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
    public UserDto updateUser(String email, User updatedUser) {
       Optional<User> user1= userRepository.findByEmail(email)
                .map(user -> {
                    if(updatedUser.getFullName()!=null)
                    user.setFullName(updatedUser.getFullName());
                    if(updatedUser.getPhoneNumber()!=null)
                    user.setPhoneNumber(updatedUser.getPhoneNumber());
                    if(updatedUser.getPassword()!=null)
                    user.setPassword(updatedUser.getPassword());


                    userRepository.save(user);
                    return user;
                });

        return userMapper.userToDto(user1.get());
    }


}
