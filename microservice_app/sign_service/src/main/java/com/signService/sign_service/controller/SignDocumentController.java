package com.signService.sign_service.controller;

import com.signService.sign_service.model.dto.SignatureAndPkcs12Form;
import com.signService.sign_service.service.SignDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

@RestController
@RequestMapping("/api/v1/sign-document")
public class SignDocumentController {

    @Autowired
    private SignDocumentService signDocumentService;

    /*GET SIGNATURE AND PKCS#12 FILE FROM DOCUMENT HASHCODE*/
    @GetMapping("/signature-pkcs12")
    public ResponseEntity<SignatureAndPkcs12Form> getSignatureAndPkcs12ByDocumentHashCode (String hashcode) {
        return ResponseEntity.ok(signDocumentService.getSignatureAndPkcs12(hashcode));
    }

    /*SIGN DOCUMENT */
    @PostMapping(path = "/sign", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> signDocument(
            @RequestParam("file")MultipartFile file,
            @RequestParam("pkcs12FileId") Long pkcs12FileId
    ) throws UnrecoverableKeyException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        return ResponseEntity.ok(signDocumentService.signSignature(file, pkcs12FileId));
    }
}
