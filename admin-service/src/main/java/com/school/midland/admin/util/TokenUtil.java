package com.school.midland.admin.util;

import com.school.midland.admin.security.JwtTokenValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenUtil {

    private final JwtTokenValidator jwtTokenProvider;

    public String extractUsername(String token) {
        return jwtTokenProvider.extractUsername(stripBearer(token));
    }

//    public String extractRole(String token) {
//        return jwtTokenProvider.getRole(stripBearer(token));
//    }
//
//    public String extractEmail(String token) {
//        return jwtTokenProvider.extractEmail(stripBearer(token));
//    }



    private String stripBearer(String token) {
        return token != null && token.startsWith("Bearer ") ? token.substring(7) : token;
    }
}