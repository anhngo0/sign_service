package com.signService.auth_service.model.dto;

import lombok.Data;

@Data
public class AccountDto {
    public AccountDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String username;
    private String password;
}
