package com.school.midland.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private Key getSignKey() {
        if (secret == null || secret.length() < 32)
            throw new IllegalStateException("JWT secret too short. Use at least 32 chars.");
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username, String role, String identifier,String email, UUID userUid) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .claim("email",email)
                .claim("associated_identifier",identifier)
                .claim("user_uid",userUid)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public String getRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    public String extractAssociateIdentifier(String token){return extractClaims(token).get("associated_identifier",String.class);}
    public UUID extractUserUid(String token){return extractClaims(token).get("user_uid", UUID.class);}
    public String extractEmail(String token){return extractClaims(token).get("email",String.class);}
    public boolean isTokenValid(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

}
