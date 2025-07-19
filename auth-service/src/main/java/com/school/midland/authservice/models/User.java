package com.school.midland.authservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_uid", updatable = false, nullable = false)
    private UUID userUid;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String username;

//    @Column(nullable = false)
    private String fullName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name="email")
    private String email;

//    @Enumerated(EnumType.STRING)
    @Column(nullable = false,columnDefinition = "ROLE_TYPE")
    private String role;

    @Column(name = "associated_identifier", unique = true, nullable = false)
    private String associatedIdentifier;

    @Column(name = "login_identifier")
    private String loginIdentifier;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // ... other user fields from your table ...

    // --- UserDetails Methods ---
    // Spring Security will use these methods

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
//    }
//
//    @Override
//    public String getPassword() { return password; }
//
//    @Override
//    public String getUsername() { return username; }
//
//    @Override
//    public boolean isAccountNonExpired() { return true; }
//
//    @Override
//    public boolean isAccountNonLocked() { return true; }
//
//    @Override
//    public boolean isCredentialsNonExpired() { return true; }
//
//    @Override
//    public boolean isEnabled() { return true; }
}
