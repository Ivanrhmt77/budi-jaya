package com.company.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    // Gunakan @Value untuk membaca secret key dari application.properties
    @Value("${jwt.secret}")
    private String secretKeyString;

    // Menghasilkan token JWT
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // Menghasilkan token JWT dengan claims tambahan
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 jam
                .signWith(getSigningKey())
                .compact();
    }

    // Mendapatkan kunci penandatangan
    private Key getSigningKey() {
        byte[] keyBytes = secretKeyString.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Ekstrak username dari token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Ekstrak klaim tertentu dari token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Ekstrak semua klaim dari token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Periksa apakah token sudah kedaluwarsa
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Ekstrak tanggal kedaluwarsa token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Validasi token
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}