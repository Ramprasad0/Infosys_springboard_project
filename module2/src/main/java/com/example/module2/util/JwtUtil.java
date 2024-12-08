package com.example.module2.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "abcdefghijklmnopqrstuvwxyzabcdefghinjkl";  // Keep secret key safe and private
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // Generate a JWT token for the authenticated user
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)  // Set the username as the subject of the token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))  // 1 day expiration
                .signWith(KEY)
                .compact();
    }

    // Validate the JWT token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Extract username from JWT token
    public String extractUsername(String token) {
        Claims claims = Jwts.parser().setSigningKey(KEY).build().parseClaimsJws(token).getBody();
        return claims.getSubject();  // Get the subject (username)
    }
}
