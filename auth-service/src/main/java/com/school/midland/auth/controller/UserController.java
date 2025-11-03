package com.school.midland.auth.controller;


import com.school.midland.auth.constants.AuthApi;
import com.school.midland.auth.dto.response.UserCreationResponse;
import com.school.midland.auth.dto.user.UserDto;
import com.school.midland.auth.dto.user.UserUpdateDto;
import com.school.midland.auth.models.User;
import com.school.midland.auth.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AuthApi.USER_URI)
public class UserController {
private final UserService userService;

    @GetMapping("/get/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
        UserDto user = userService.getUser(username);
        return ResponseEntity.ok(user);
    }

    //    @PutMapping("/update/{username}")
    @GetMapping("/schoolEmail/{email}")
    public ResponseEntity<UserDto> getBySchoolEmail(@PathVariable(name = "email")String schoolEmail){
        return ResponseEntity.ok(userService.getByEmail(schoolEmail));
    }

    @DeleteMapping("/delete/{schoolEmail}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable(name = "schoolEmail") String schoolEmail,
                                              @RequestHeader("Authorization") String token){
        System.out.println("helo brosss");
        boolean deleted=userService.deleteUser(schoolEmail);
        System.out.println(deleted);
        return deleted
                ? ResponseEntity.status(HttpStatus.OK).body(true)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<UserUpdateDto> updateUser(@PathVariable String email,
                                                    @RequestBody UserUpdateDto updatedUser) {
        UserUpdateDto user = userService.updateUser(email, updatedUser);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
