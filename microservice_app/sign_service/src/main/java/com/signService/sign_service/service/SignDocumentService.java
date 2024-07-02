package com.signService.sign_service.service;

import com.signService.sign_service.model.dto.SignatureAndPkcs12Form;
import com.signService.sign_service.model.entity.Pkcs12File;
import com.signService.sign_service.model.entity.SignDocument;
import com.signService.sign_service.repository.Pkcs12FileRepository;
import com.signService.sign_service.repository.SignDocumentRepository;
import com.signService.sign_service.utils.UtilsFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Base64;

@Service
public class SignDocumentService {

    @Autowired
    private Pkcs12FileRepository pkcs12FileRepository;

    @Autowired
    private SignDocumentRepository signDocumentRepository;

    public String signSignature(MultipartFile file, Long pkcs12Id) throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException, UnrecoverableKeyException, InvalidKeyException, SignatureException {
       final Pkcs12File pkcs12File = pkcs12FileRepository.findById(pkcs12Id)
                .orElseThrow(() -> new RuntimeException("No file pkcs12 found with id " + pkcs12Id));

        // Load the PKCS12 file
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(new ByteArrayInputStream(pkcs12File.getData()), pkcs12File.getPassword().toCharArray());

        // Get the private key
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(pkcs12File.getAlias(), pkcs12File.getPassword().toCharArray());

        // Sign the PDF hash
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(UtilsFunction.hashFile(file.getBytes()));
        byte[] signedHash = signature.sign();

        //save the signature
        signDocumentRepository.save(new SignDocument(
                Base64.getEncoder().encodeToString(UtilsFunction.hashFile(file.getBytes())),
                pkcs12File,
                signedHash
        ));
         return Base64.getEncoder().encodeToString(signedHash);
    }

    public SignatureAndPkcs12Form getSignatureAndPkcs12(String hashcode){
        SignDocument signDocument = signDocumentRepository.findByDocumentHashcode(hashcode)
                .orElseThrow(() -> new RuntimeException("No sign_document found with document hashcode " + hashcode));

        return new SignatureAndPkcs12Form(
                Base64.getEncoder().encodeToString(signDocument.getSignature()),
                Base64.getEncoder().encodeToString(signDocument.getPkcs12().getData())
        );
    }
}
