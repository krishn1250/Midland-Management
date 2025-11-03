package com.school.midland.admin.validator.admin;

import com.school.midland.admin.dtos.admin.AdminDto;
import com.school.midland.admin.exception.AdminException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AdminValidator {

    public void validateCreateAdmin(AdminDto dto) {
        if (dto == null)
            throw new AdminException("Admin DTO cannot be null", HttpStatus.BAD_REQUEST);

        if (!StringUtils.hasText(dto.getUsername()))
            throw new AdminException("Username is required", HttpStatus.BAD_REQUEST);

        if (!StringUtils.hasText(dto.getPassword()))
            throw new AdminException("Password is required", HttpStatus.BAD_REQUEST);

//        if (!StringUtils.hasText(dto.getPhoneNumber()))
//            throw new AdminException("Phonenumber is required", HttpStatus.BAD_REQUEST);

        if (!StringUtils.hasText(dto.getEmail()) || !dto.getEmail().matches("^[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,6}$"))
            throw new AdminException("Invalid email format", HttpStatus.BAD_REQUEST);
    }
    public void validateGetAdmin(){


    }
}