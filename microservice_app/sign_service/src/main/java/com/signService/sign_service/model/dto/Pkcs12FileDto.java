package com.signService.sign_service.model.dto;

import lombok.Data;

@Data
public class Pkcs12FileDto {
    private Long id;

    public Pkcs12FileDto(Long id) {
        this.id = id;
    }
}
