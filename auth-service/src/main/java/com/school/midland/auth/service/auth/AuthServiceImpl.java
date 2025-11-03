package com.school.midland.auth.service.auth;

import com.school.midland.auth.dto.request.LoginRequest;
import com.school.midland.auth.dto.request.RegisterRequest;
import com.school.midland.auth.dto.response.AuthResponse;
import com.school.midland.auth.dto.response.LoginResponse;
import com.school.midland.auth.dto.response.UserCreationResponse;
import com.school.midland.auth.exception.AuthException;
import com.school.midland.auth.mapper.AuthMapper;
import com.school.midland.auth.models.User;
import com.school.midland.auth.repository.AuthRepository;
import com.school.midland.auth.security.JwtTokenProvider;
import com.school.midland.auth.validators.AuthRequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements  AuthService{
    private static final Logger auditLogger = LoggerFactory.getLogger("AUDIT_LOGGER");
    private final AuthRepository authRepository;
private final AuthMapper authMapper;
private  final JwtTokenProvider jwtTokenProvider;
private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserCreationResponse userSignup(RegisterRequest request) {
        log.info("Attempting signup for user: {}", request.getEmail());
        AuthRequestValidator.validateRegisterRequest(request);

        // Check if username already exists
        authRepository.findByUsername(request.getUsername())

                .ifPresent(u -> {
                    auditLogger.info("Signup failed for username: {}. Reason: Username already exists.", request.getUsername());
                    throw new AuthException("Username already exists", HttpStatus.CONFLICT); });

        authRepository.findByEmail(request.getEmail())
                .ifPresent(u -> {
                    auditLogger.info("Signup failed for email: {}. Reason: Email already exists.", request.getEmail());
                    throw new AuthException("Email already exists", HttpStatus.CONFLICT); });

        try {
            String encodedPassword = passwordEncoder.encode(request.getPassword());
            User user = authMapper.toUserEntity(request, encodedPassword);

            User saved = authRepository.save(user);

            String token = jwtTokenProvider.generateToken(
                    saved.getUsername(),
                    saved.getRole(),
                    saved.getAssociatedIdentifier(),
                    saved.getEmail(),
                    saved.getUserUid()
            );

            UserCreationResponse response = UserCreationResponse.builder()
                    .username(saved.getUsername())
                    .role(saved.getRole())
                    .userUid(saved.getUserUid())
                    .email(saved.getEmail())
                    .fullName(saved.getFullName())
                    .message("User created successfully")
                    .token(token)
                    .build();

            log.info("User created: {}", saved.getUsername());
            return response;

        } catch (DataIntegrityViolationException e) {
            log.error("Duplicate entry detected: {}", e.getMessage());
            throw new AuthException("Duplicate user entry", HttpStatus.CONFLICT);
        } catch (Exception e) {
            log.error("Unexpected error during signup", e);
            throw new AuthException("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponse userSignin(LoginRequest request, String role) {
        AuthRequestValidator.validateLoginRequest(request);

        final String finalRole = role.toUpperCase();
        log.info("User login attempt for role: {}", finalRole);

        User user = null;

        if (request.getUsername() != null) {
            user = authRepository.findByUsernameAndRole(request.getUsername(), finalRole)
                    .orElseThrow(() -> new AuthException("Invalid username for " + finalRole, HttpStatus.UNAUTHORIZED));
        } else if (request.getEmail() != null) {
            user = authRepository.findByEmailAndRole(request.getEmail(), finalRole)
                    .orElseThrow(() -> new AuthException("Invalid email for " + finalRole, HttpStatus.UNAUTHORIZED));
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AuthException("Invalid password", HttpStatus.UNAUTHORIZED);
        }

        if (!Boolean.TRUE.equals(user.getIsActive())) {
            throw new AuthException("User account inactive", HttpStatus.FORBIDDEN);
        }

        String token = jwtTokenProvider.generateToken(
                user.getUsername(),
                user.getRole(),
                user.getAssociatedIdentifier(),
                user.getEmail(),
                user.getUserUid()
        );

        log.info("User '{}' logged in successfully", user.getUsername());

        return LoginResponse.builder()
                .username(user.getUsername())
                .message("Successfully logged in")
                .token(token)
                .role(user.getRole())
                .build();
    }

}
