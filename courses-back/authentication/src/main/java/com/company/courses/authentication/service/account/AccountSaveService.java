package com.company.courses.authentication.service.account;

import com.company.courses.authentication.model.Account;
import com.company.courses.authentication.model.enums.AccountTypeEnum;
import com.company.courses.authentication.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountSaveService {

    private final AccountRepository accountRepository;

    public AccountSaveService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account saveUser(String authId, String userId) {
        return this.accountRepository.save(new Account(authId, userId, AccountTypeEnum.PERSONAL));
    }
}
