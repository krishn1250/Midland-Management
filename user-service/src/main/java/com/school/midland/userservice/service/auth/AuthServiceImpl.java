package com.school.midland.userservice.service.auth;

import com.school.midland.constants.Role;
import com.school.midland.userservice.dto.AuthResponse;
import com.school.midland.userservice.dto.LoginRequest;
import com.school.midland.userservice.dto.SignupRequest;
import com.school.midland.userservice.models.User;
import com.school.midland.userservice.repository.UserRepository;
import com.school.midland.userservice.security.JwtTokenProvider;
import com.school.midland.userservice.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    @Override
    public AuthResponse signup(SignupRequest request) {
        Optional<UserDetails> existing = userRepository.findByUsername(request.getUsername());
        if (existing.isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user=User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.valueOf(request.getRole().toUpperCase()))
                .role(Role.valueOf("STUDENT"))
                .Email(request.getEmail())
                .associatedIdentifier(request.getAssociatedIdentifier())
                .userUid(UUID.randomUUID()).
        build();

        userRepository.save(user);
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtTokenProvider.generateToken(userDetails);
        return AuthResponse.builder().token(token).role(""+user.getRole())
                .message("Success").build();
    }

    @Override
    public AuthResponse signin(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        User user = (User) userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtTokenProvider.generateToken(userDetails);
        return AuthResponse.builder().token(token).Username(user.getUsername()).message("Sign in Success")
                .build();
    }
}
