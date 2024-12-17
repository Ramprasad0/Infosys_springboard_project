package com.infosys.community.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class Provider {
    private static final SecretKey key = Keys.hmacShaKeyFor(Constants_JWT.SECRET_STRING.getBytes());
    private static final String ISSUER = "CommunityManagementSystem";

    public static String generateToken(Authentication auth) {
        String jwt= Jwts.builder()
                .setIssuer(ISSUER)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000)) // Token expiration: 1 day
                .claim("email", auth.getName()) // Add email to claims
                .signWith(key)
                .compact();

        return jwt;
    }


    public static String getEmailFromJwtToken(String jwt) {
        // Remove "Bearer " prefix
        if (jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
        }

        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        String email=String.valueOf(claims.get("email"));
        return email; // Extract email claim
    }
}
