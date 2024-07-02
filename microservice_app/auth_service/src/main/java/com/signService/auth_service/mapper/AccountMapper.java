package com.signService.auth_service.mapper;

import com.signService.auth_service.model.dto.AccountDto;
import com.signService.auth_service.model.enntity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account toUser(AccountDto accountDto){
        return new Account(
                accountDto.getUsername(),
                passwordEncoder.encode(accountDto.getPassword())
        );
    }

}
