package com.school.midland.authservice.service.auth;

import com.school.midland.authservice.dto.request.LoginRequest;
import com.school.midland.authservice.dto.request.RegisterRequest;
import com.school.midland.authservice.dto.response.AuthResponse;
import com.school.midland.authservice.dto.response.LoginResponse;
import com.school.midland.authservice.exception.AuthException;
import com.school.midland.authservice.mapper.AuthMapper;
import com.school.midland.authservice.models.User;
import com.school.midland.authservice.repository.UserRepository;
import com.school.midland.authservice.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements  AuthService{

    private final UserRepository userRepository;
private final AuthMapper authMapper;
private  final JwtTokenProvider jwtTokenProvider;
private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> userSignup(RegisterRequest request) {
        System.out.println(request);
        if (request == null || request.getUsername()==null || request.getPassword()==null ) {
            throw new AuthException("fill the required details", HttpStatus.BAD_REQUEST);
        }

        // Check if username already exists
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new AuthException("Username already exists", HttpStatus.CONFLICT);
        }
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new AuthException("email already exists", HttpStatus.CONFLICT);
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user=authMapper.toUserEntity(request,encodedPassword);
        User saved=userRepository.save(user);
        final var token = jwtTokenProvider.generateToken(saved.getUsername(), String.valueOf(saved.getRole()));
        AuthResponse authResponse=AuthResponse.builder()
                .username(saved.getUsername())
                .role(String.valueOf(saved.getRole()))
                .userUid(saved.getUserUid())
                .token(token)
                .build();

        System.out.println(authResponse);
        return ResponseEntity.ok(authResponse);
    }

    @Override
    public ResponseEntity<?> userSignin(LoginRequest request) {
        if (request == null) {
            throw new AuthException("Request cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (request.getUsername() == null || request.getPassword() == null) {
            throw new AuthException("Username or password missing", HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AuthException("Invalid username or password", HttpStatus.UNAUTHORIZED));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AuthException("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }

        final var token = jwtTokenProvider.generateToken(user.getUsername(), String.valueOf(user.getRole()));

        LoginResponse loginResponse = LoginResponse.builder()
                .username(user.getUsername())
                .message("Successfully logged in")
                .token(token)
                .role(String.valueOf(user.getRole()))
                .build();

        return ResponseEntity.ok(loginResponse);
    }
}
