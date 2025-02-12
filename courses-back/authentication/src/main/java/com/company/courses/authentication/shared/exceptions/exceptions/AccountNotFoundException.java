package com.company.courses.authentication.shared.exceptions.exceptions;

import com.company.courses.authentication.shared.exceptions.BaseException;
import com.company.courses.authentication.shared.exceptions.ExceptionCode;
import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends BaseException {
    public AccountNotFoundException(String message) {
        super(false, HttpStatus.NOT_FOUND, message, ExceptionCode.ACCOUNT_NOT_FOUND);
    }
}
