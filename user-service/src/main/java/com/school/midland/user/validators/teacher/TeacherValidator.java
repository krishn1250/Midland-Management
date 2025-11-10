package com.school.midland.user.validators.teacher;

import com.school.midland.user.dto.teacher.TeacherDto;
import com.school.midland.user.exception.UserException;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public class TeacherValidator {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static void validateTeacherData(TeacherDto dto) {
        if (dto == null)
            throw new UserException("Teacher details cannot be null", HttpStatus.BAD_REQUEST);


        Map<String,String> dataMap=new HashMap<>();
        dataMap.put("teacherCode",dto.getTeacherCode());
        dataMap.put("schoolEmail",dto.getSchoolEmail());
        dataMap.put("teacherCode",dto.getTeacherCode());
        dataMap.put("schoolCode",dto.getSchoolCode());
        dataMap.put("userName",dto.getUsername());
        dataMap.put("password",dto.getPassword());
//        dataMap.put("phoneNumber",dto.getPhoneNumber());

        validateData(dataMap);
    }
    public static void validateUpdateTeacherData(TeacherDto dto) {
        if (dto == null)
            throw new UserException("Teacher details cannot be null", HttpStatus.BAD_REQUEST);


        Map<String,String> dataMap=new HashMap<>();

        dataMap.put("schoolEmail",dto.getSchoolEmail());
        dataMap.put("schoolCode",dto.getSchoolCode());
        dataMap.put("personalEmail",dto.getPersonalEmail());
        dataMap.put("designation",dto.getDesignation());
        dataMap.put("department",dto.getDepartment());
        dataMap.put("fullName",dto.getFullName());
        dataMap.put("profileImage",dto.getProfileImage());
        dataMap.put("qualification",dto.getQualification());
        dataMap.put("phoneNumber",dto.getPhoneNumber());
       Boolean isValid=false;
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            if (validateValue(entry.getValue())) {
                isValid = true;
                break;
            }
        }

        if (!isValid)
            throw new UserException("At least one field is required", HttpStatus.BAD_REQUEST);
    }

    public static void validateTeacherCode(String teacherCode) {
        if (teacherCode == null || teacherCode.isEmpty())
            throw new UserException("Teacher code cannot be empty", HttpStatus.BAD_REQUEST);
    }

    public static void validateEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new UserException("Invalid email format", HttpStatus.BAD_REQUEST);
        }
    }
    public static boolean validateValue(String value){
        if( value==null || value.isBlank() || value.isEmpty()){
            return false;
        }
        return true;
    }
    public static void validateData(Map<String,String> map) {
        map.forEach((key,value)->{
            if(value==null || value.isEmpty() || value.isBlank()){
                throw new UserException(key+" cannot be empty", HttpStatus.BAD_REQUEST);
            }
        });

    }
}
