package com.school.midland.auth.utils;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

    private ResponseUtil() {}

    public static ResponseEntity<Map<String, Object>> success(Object data, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.OK.value());
        body.put("message", message);
        body.put("data", data);
        return ResponseEntity.ok(body);
    }

    public static ResponseEntity<Map<String, Object>> created(Object data, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.CREATED.value());
        body.put("message", message);
        body.put("data", data);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    public static ResponseEntity<Map<String, Object>> deleted(String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NO_CONTENT.value());
        body.put("message", message);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(body);
    }
}
