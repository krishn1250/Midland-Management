package com.school.midland.authservice.controller;


import com.school.midland.authservice.constants.AuthApi;
import com.school.midland.authservice.dto.response.UserCreationResponse;
import com.school.midland.authservice.models.User;
import com.school.midland.authservice.service.user.UserService;
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
    public ResponseEntity<UserCreationResponse> getUser(@PathVariable String username) {
        User user = userService.getUser(username);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserCreationResponse response = UserCreationResponse.builder()
                .userUid(user.getUserUid())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();

        return ResponseEntity.ok(response);
    }

//    @PutMapping("/update/{username}")
@GetMapping("/schoolEmail/{email}")
public ResponseEntity<User> getBySchoolEmail(@PathVariable(name = "schoolEmail")String schoolEmail){
    return ResponseEntity.ok(userService.getByEmail(schoolEmail));
}

    @DeleteMapping("/delete/{schoolEmail}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable(name = "schoolEmail") String schoolEmail,
                                              @RequestHeader("Authorization") String token){
        System.out.println("helo brosss");
        boolean deleted=userService.deleteUser(schoolEmail);
        System.out.println(deleted);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.OK).body(true);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }


}
