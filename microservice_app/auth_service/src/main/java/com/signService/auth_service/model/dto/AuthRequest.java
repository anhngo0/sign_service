package com.signService.auth_service.model.dto;

import lombok.Data;

@Data
public class AuthRequest {

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String username;
    private String password;
}
