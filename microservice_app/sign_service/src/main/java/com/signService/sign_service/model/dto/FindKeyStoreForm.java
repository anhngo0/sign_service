package com.signService.sign_service.model.dto;

import lombok.Data;

@Data
public class FindKeyStoreForm {
    private String alias;
    private String password;
}
