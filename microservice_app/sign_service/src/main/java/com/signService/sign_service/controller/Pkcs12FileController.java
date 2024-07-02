package com.signService.sign_service.controller;

import com.signService.sign_service.model.dto.FindPkcs12Form;
import com.signService.sign_service.model.dto.Pkcs12FileDto;
import com.signService.sign_service.model.entity.Pkcs12File;
import com.signService.sign_service.service.Pkcs12FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pkcs12")
public class Pkcs12FileController {

    @Autowired
    private Pkcs12FileService pkcs12FileService;

    /*GET PKCS12 FILE BY ALIAS AND PASSWORD*/
    @GetMapping("/pkcs12")
    public ResponseEntity<Pkcs12FileDto> getPkcs12FileByAliasAndPassword(@RequestBody FindPkcs12Form findPkcs12Form){
        return ResponseEntity.ok(pkcs12FileService.getPkcs12ByAliasAndPassword(findPkcs12Form));
    }
}
