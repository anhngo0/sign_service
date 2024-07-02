package com.signService.sign_service.model.dto;

import lombok.Data;

@Data
public class FindPkcs12Form {
    private String alias;
    private String password;

    public FindPkcs12Form(String alias, String password) {
        this.alias = alias;
        this.password = password;
    }
}
