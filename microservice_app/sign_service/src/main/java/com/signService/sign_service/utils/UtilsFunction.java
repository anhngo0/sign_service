package com.signService.sign_service.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Base64;

@Component
public class UtilsFunction {

    public static Certificate[] getCertificatesFromPkcs12File(KeyStore keyStore, String alias, String password){
        try {
            keyStore.load(null, password.toCharArray());
            Certificate[] chain = keyStore.getCertificateChain(alias);
            if (chain == null) {
                throw new KeyStoreException("Certificate chain not found for alias: " + alias);
            }
            return chain;
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException e) {
            throw new RuntimeException("Error retrieving certificate chain", e);
        }
    }

    public static PrivateKey getPrivateKeyFromPkcs12File(KeyStore keyStore, String alias, String password){
        try  {
            keyStore.load(null, password.toCharArray());

            return (PrivateKey) keyStore.getKey(alias, password.toCharArray());

        } catch (CertificateException | IOException | NoSuchAlgorithmException | UnrecoverableKeyException |
                 KeyStoreException e) {
            throw new RuntimeException(e);
        }
    }


    public static byte[] hashFile(byte[] fileContent) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(fileContent);
    }

}
