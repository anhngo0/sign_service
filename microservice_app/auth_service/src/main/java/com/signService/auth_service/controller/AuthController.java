package com.signService.auth_service.controller;


import com.signService.auth_service.model.dto.AuthRequest;
import com.signService.auth_service.model.dto.AuthResponse;
import com.signService.auth_service.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<AuthResponse> login(AuthRequest loginForm, HttpServletResponse response){
        return ResponseEntity.ok(authService.authenticate(loginForm, response));
    }

    @PostMapping(value = "/validate-token")
    public ResponseEntity<?> validateToken(HttpServletRequest request){
        return authService.validateToken(request);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(HttpServletRequest request){
        return ResponseEntity.ok(authService.refreshToken(request));
    }
}
