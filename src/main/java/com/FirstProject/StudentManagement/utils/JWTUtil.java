package com.FirstProject.StudentManagement.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {

    private String SECRET_KEY = "fnGNWpZ506p05DEDSERpkdnXHbx7ibJpDwSoIfn2gCr";

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());
        claims.put("authorities", userDetails.getAuthorities());
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String username) {
        // Implementation for token creation
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .header().empty().add("typ", "jwt") // Add the "typ" header)
                .and()
                .issuedAt(new java.util.Date(System.currentTimeMillis()))
                .setExpiration(new java.util.Date(System.currentTimeMillis() + 1000 * 60 * 2)) // Token valid for 10 hours
                .signWith(getSigningKey()) // Use a
                .compact();
    }

    private Key getSigningKey() {
        // Implementation for getting the signing key
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
