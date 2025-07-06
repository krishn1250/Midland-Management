package com.school.midland.adminservice.service;

import com.school.midland.adminservice.client.service.UserServiceClient;
import com.school.midland.adminservice.client.user.dto.UserCreationRequest;
import com.school.midland.adminservice.dto.AdminLoginRequest;
import com.school.midland.adminservice.dto.AdminSignupRequest;
import com.school.midland.adminservice.dto.AuthResponse;
import com.school.midland.adminservice.mapper.AdminMapper;
import com.school.midland.adminservice.models.Admin;
import com.school.midland.adminservice.repository.AdminRepository;
import com.school.midland.adminservice.security.JwtTokenProvider;
import com.school.midland.common.utils.UuidGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserServiceClient usersServiceClient;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public AuthResponse signup(AdminSignupRequest request) {
        if (adminRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
//        UUID userUid = UuidGenerator.generate();
        UUID adminUid = UuidGenerator.generate();

        UserCreationRequest userReq= UserCreationRequest.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .phoneNumber(request.getPhone())
                .role("ADMIN")
                .associatedIdentifier("ADM"+adminUid.toString().substring(0,6))
                .build();

        UUID userResp = usersServiceClient.createUser(userReq);

if(userResp==null){
    throw new RuntimeException("Unable to store in users table");
}
        Admin admin =adminMapper.requesttoEntity(request);
        admin.setUserUid(userResp);
        admin.setAdminUid(adminUid);
        Admin saved = adminRepository.save(admin);

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User springUser = new User(saved.getUsername(), saved.getPassword(), List.of(new SimpleGrantedAuthority("ADMIN")));
        String token = jwtTokenProvider.generateToken(springUser, saved.getAdminUid());

        return new AuthResponse(token,"ADMIN","sucess");
    }

    @Override
    public AuthResponse signin(AdminLoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        Admin admin = adminRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        User springUser = new User(admin.getUsername(), admin.getPassword(), List.of(new SimpleGrantedAuthority("ADMIN")));
        String token = jwtTokenProvider.generateToken(springUser, admin.getAdminUid());

        return new AuthResponse(token,"ADMIN","success");
    }
}
