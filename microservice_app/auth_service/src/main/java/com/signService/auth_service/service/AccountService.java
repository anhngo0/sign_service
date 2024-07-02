package com.signService.auth_service.service;

import com.signService.auth_service.mapper.AccountMapper;
import com.signService.auth_service.model.dto.AccountDto;
import com.signService.auth_service.model.enntity.Account;
import com.signService.auth_service.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Error! No resource found!"));
    }


    public void createUser(AccountDto accountDto) {
        Account newAccount = accountMapper.toUser(accountDto);
        newAccount = accountRepository.save(newAccount);
    }
}
