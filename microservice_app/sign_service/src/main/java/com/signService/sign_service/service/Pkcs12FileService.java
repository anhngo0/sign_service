package com.signService.sign_service.service;

import com.signService.sign_service.model.dto.FindPkcs12Form;
import com.signService.sign_service.model.dto.Pkcs12FileDto;
import com.signService.sign_service.model.entity.Pkcs12File;
import com.signService.sign_service.repository.Pkcs12FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Pkcs12FileService {


    @Autowired
    private Pkcs12FileRepository pkcs12FileRepository;

    public Pkcs12FileDto getPkcs12ByAliasAndPassword(FindPkcs12Form findPkcs12Form){
        Pkcs12File pkcs12File = pkcs12FileRepository.findByAliasAndPassword(
                findPkcs12Form.getAlias(),
                findPkcs12Form.getPassword()
                )
                .orElseThrow(()-> new RuntimeException("No keystore has found"));
        return new Pkcs12FileDto(pkcs12File.getId());
    }
}
