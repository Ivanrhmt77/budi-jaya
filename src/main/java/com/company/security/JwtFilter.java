package com.company.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.company.services.AuthService;
import com.company.services.TokenBlacklistService;
import com.company.utils.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            // Ekstrak token dari header Authorization
            final String authorizationHeader = request.getHeader("Authorization");

            String username = null;
            String jwt = null;

            // Periksa dan ekstrak token
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                jwt = authorizationHeader.substring(7);
                
                // Periksa apakah token di-blacklist
                if (tokenBlacklistService.isTokenBlacklisted(jwt)) {
                    log.warn("Blacklisted token attempt: {}", jwt);
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token has been invalidated");
                    return;
                }

                try {
                    // Ekstrak username dari token
                    username = jwtUtil.extractUsername(jwt);
                } catch (ExpiredJwtException e) {
                    log.warn("JWT Token expired: {}", e.getMessage());
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token has expired");
                    return;
                } catch (JwtException e) {
                    log.error("JWT Token error: {}", e.getMessage());
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Invalid token");
                    return;
                }
            }

            // Jika username valid dan belum ada autentikasi
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Muat detail pengguna
                UserDetails userDetails = authService.loadUserByUsername(username);

                // Validasi token
                if (jwtUtil.validateToken(jwt, userDetails)) {
                    // Buat authentication token
                    UsernamePasswordAuthenticationToken authToken = 
                        new UsernamePasswordAuthenticationToken(
                            userDetails, 
                            null, 
                            userDetails.getAuthorities()
                        );

                    // Set detail autentikasi
                    authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    // Set authentication ke security context
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    log.info("User authenticated: {}", username);
                }
            }

            // Lanjutkan filter chain
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            log.error("Authentication error", e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Authentication failed");
        }
    }

    /**
     * Metode tambahan untuk ekstraksi token dari request
     */
    private String extractTokenFromRequest(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        
        return null;
    }
}