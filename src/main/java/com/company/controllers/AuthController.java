package com.company.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.dto.AkunData;
import com.company.dto.LoginRequest;
import com.company.dto.ResponseData;
import com.company.models.entities.Akun;
import com.company.services.AuthService;
import com.company.services.TokenBlacklistService;
import com.company.utils.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<Akun>> register(@RequestBody AkunData akunData) {
        ResponseData<Akun> response = new ResponseData<>();
        Akun akun = modelMapper.map(akunData, Akun.class);
        response.setPayload(authService.registerAkun(akun));
        response.setStatus(true);
        response.getMessages().add("User Saved!");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData<Akun>> login(@RequestBody LoginRequest loginRequest) {
        ResponseData<Akun> response = new ResponseData<>();
        
        try {
            // Attempt to authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(), 
                    loginRequest.getPassword()
                )
            );
            
            // Set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            // Retrieve the authenticated user
            Akun authenticatedUser = (Akun) authentication.getPrincipal();
            
            // Generate the JWT token using UserDetails
            String token = jwtUtil.generateToken(authenticatedUser);

            // Return the token along with user details
            response.setPayload(authenticatedUser);
            response.setStatus(true);
            response.getMessages().add("Login successful!");
            response.getMessages().add("Token: " + token);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus(false);
            response.getMessages().add("Login failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseData<Void>> logout(HttpServletRequest request) {
        ResponseData<Void> response = new ResponseData<>();
        
        try {
            // Ekstrak token dari header Authorization
            String authHeader = request.getHeader("Authorization");
            
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                // Ekstrak token aktual (tanpa prefix "Bearer ")
                String token = authHeader.substring(7);
                
                // Tambahkan token ke blacklist
                tokenBlacklistService.blacklistToken(token);
                
                // Hapus konteks keamanan
                SecurityContextHolder.clearContext();
                
                response.setStatus(true);
                response.getMessages().add("Logout successful!");
                return ResponseEntity.ok(response);
            }
            
            response.setStatus(false);
            response.getMessages().add("Logout failed: No active token");
            return ResponseEntity.badRequest().body(response);
        
        } catch (Exception e) {
            response.setStatus(false);
            response.getMessages().add("Logout error: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

}
