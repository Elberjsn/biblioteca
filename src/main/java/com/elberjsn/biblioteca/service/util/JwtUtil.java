package com.elberjsn.biblioteca.service.util;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
    private static final SecretKey key = Keys.hmacShaKeyFor("b73d9a41-9f57-45c2-90ad-e6a7f07b8e2c".getBytes());

    public static String geradorToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);

        claims.put("exp", System.currentTimeMillis() + 1000 * 60 * 60*12); 

        return Jwts.builder()
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public static boolean validadorToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;

        }
    }
}
