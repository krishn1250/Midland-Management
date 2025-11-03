package com.school.midland.auth.validators;

import com.school.midland.auth.dto.user.UserUpdateDto;
import com.school.midland.auth.exception.AuthException;
import org.springframework.http.HttpStatus;

public class UserValidator {

    // Validate user update request
    public static void validateUserUpdate(UserUpdateDto updatedUser) {
        if (updatedUser == null) {
            throw new AuthException("User update details cannot be null", HttpStatus.BAD_REQUEST);
        }

        if (updatedUser.getFullName() == null && updatedUser.getPhoneNumber() == null &&
                updatedUser.getPassword() == null && updatedUser.getEmail() == null) {
            throw new AuthException("At least one field must be updated", HttpStatus.BAD_REQUEST);
        }

        // Additional validations can be added here
    }
}
