package com.signService.auth_service.controller;

import com.signService.auth_service.model.dto.AccountDto;
import com.signService.auth_service.model.enntity.Account;
import com.signService.auth_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser (@RequestBody AccountDto accountDto) {
        accountService.createUser(accountDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/current-user")
    public ResponseEntity<Long> getCurrentUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new RuntimeException("Need to login to get current user id");
        }

        if (authentication.getPrincipal() instanceof UserDetails) {
            Account userDetails = (Account) authentication.getPrincipal();
            return ResponseEntity.ok(userDetails.getId());
        }

        throw new RuntimeException("Need to login to get current user id");
    }
}
