package com.fpoly.kickshop.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "4slUku3RnuaJPG2w8s+BdwEcZRa982Mvy1S721E6kFc=";

    // Thời gian sống của token (milliseconds)
    private final long ACCESS_TOKEN_VALIDITY = 1000 * 60 * 60 * 2; // 1 giờ
    private final long REFRESH_TOKEN_VALIDITY = 1000 * 60 * 60 * 24 * 30; // 7 ngày

    // Tạo Access Token
    public String generateAccessToken(Integer id, String email) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("email", email);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(id.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Tạo Refresh Token
    public String generateRefreshToken(Integer id) {
        return Jwts.builder()
                .setSubject(id.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Trích xuất ID từ token
    public String extractId(String token) {
        return extractClaims(token).getSubject();
    }

    // Trích xuất Email từ claims
    public String extractEmail(String token) {
        return (String) extractClaims(token).get("email");
    }

    // Kiểm tra tính hợp lệ của token
    public boolean validateToken(String token, String id) {
        return id.equals(extractId(token)) && !isTokenExpired(token);
    }

    // Kiểm tra token hết hạn chưa
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Trích xuất thông tin claims từ token
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
