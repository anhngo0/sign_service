package com.signService.sign_service.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SignDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentHashCode;

    @ManyToOne(targetEntity = Pkcs12File.class)
    @JoinColumn(name = "pkcs12_id")
    private Pkcs12File pkcs12;

    @Lob
    private byte[] signature;

    public SignDocument(String documentHashCode, Pkcs12File pkcs12, byte[] signature) {
        this.documentHashCode = documentHashCode;
        this.pkcs12 = pkcs12;
        this.signature = signature;
    }
}
