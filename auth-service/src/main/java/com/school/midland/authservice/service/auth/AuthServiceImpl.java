package com.school.midland.authservice.service.auth;

import com.school.midland.authservice.dto.request.LoginRequest;
import com.school.midland.authservice.dto.request.RegisterRequest;
import com.school.midland.authservice.dto.response.AuthResponse;
import com.school.midland.authservice.dto.response.LoginResponse;
import com.school.midland.authservice.exception.AuthException;
import com.school.midland.authservice.mapper.AuthMapper;
import com.school.midland.authservice.models.User;
import com.school.midland.authservice.repository.AuthRepository;
import com.school.midland.authservice.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements  AuthService{

    private final AuthRepository authRepository;
private final AuthMapper authMapper;
private  final JwtTokenProvider jwtTokenProvider;
private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> userSignup(RegisterRequest request) {
        System.out.println(request);
        if (request == null || (request.getUsername()==null && request.getEmail()==null) || request.getPassword()==null ) {
            throw new AuthException("fill the required details", HttpStatus.BAD_REQUEST);
        }

        // Check if username already exists
        if (authRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new AuthException("Username already exists", HttpStatus.CONFLICT);
        }
        if(authRepository.findByEmail(request.getEmail()).isPresent()){
            throw new AuthException("email already exists", HttpStatus.CONFLICT);
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user=authMapper.toUserEntity(request,encodedPassword);
        User saved= authRepository.save(user);
        final var token = jwtTokenProvider.generateToken(saved.getUsername(), saved.getRole(),saved.getAssociatedIdentifier(),saved.getUserUid());
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
    public ResponseEntity<?> userSignin(LoginRequest request,String Role) {
        if (request == null) {
            throw new AuthException("Request cannot be null", HttpStatus.BAD_REQUEST);
        }
        if ((request.getUsername() == null && request.getEmail()==null) || request.getPassword() == null) {
            throw new AuthException("Userid or password missing", HttpStatus.BAD_REQUEST);
        }
        User user=null;
        String finalrole = Role.toUpperCase();
        if(request.getUsername()!=null){
             user = authRepository.findByUsernameAndRole(request.getUsername(),finalrole)
                    .orElseThrow(() -> new AuthException("Invalid username for "+finalrole, HttpStatus.UNAUTHORIZED));
        }
        else if(request.getEmail()!=null){
            user = authRepository.findByEmailAndRole(request.getEmail(),finalrole)
                    .orElseThrow(() -> new AuthException("Invalid email for "+finalrole, HttpStatus.UNAUTHORIZED));
        }


        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AuthException("Invalid password ", HttpStatus.UNAUTHORIZED);
        }


        final var token = jwtTokenProvider.generateToken(user.getUsername(), user.getRole(),user.getAssociatedIdentifier(),user.getUserUid());

        LoginResponse loginResponse = LoginResponse.builder()
                .username(user.getUsername())
                .message("Successfully logged in")
                .token(token)
                .role(String.valueOf(user.getRole()))
                .build();

        return ResponseEntity.ok(loginResponse);
    }
}
