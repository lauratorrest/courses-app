package com.company.courses.authentication.service.account;

import com.company.courses.authentication.model.Account;
import com.company.courses.authentication.repository.AccountRepository;
import com.company.courses.authentication.shared.exceptions.ExceptionCode;
import com.company.courses.authentication.shared.exceptions.exceptions.AccountNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AccountGetService {

    private final AccountRepository accountRepository;
    private final MessageSource messageSource;

    public AccountGetService(AccountRepository accountRepository, MessageSource messageSource) {
        this.accountRepository = accountRepository;
        this.messageSource = messageSource;
    }

    public Account getByAuthId(String authId) {
        return this.accountRepository.findByAuthId(authId).orElseThrow(() ->
                new AccountNotFoundException(messageSource.getMessage(ExceptionCode.ACCOUNT_NOT_FOUND.getType(), null, LocaleContextHolder.getLocale())));
    }
}
