package com.signService.sign_service.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Pkcs12File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] data;

    private Long userId;
    private String alias;
    private String password;
}
