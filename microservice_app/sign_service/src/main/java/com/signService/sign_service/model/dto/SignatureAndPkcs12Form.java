package com.signService.sign_service.model.dto;

import lombok.Data;

@Data
public class SignatureAndPkcs12Form {

    private String signature;
    private String pkcs12File;

    public SignatureAndPkcs12Form(String signature, String pkcs12File) {
        this.signature = signature;
        this.pkcs12File = pkcs12File;
    }
}
