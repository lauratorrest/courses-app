package com.company.courses.authentication.shared.exceptions.exceptions;

import com.company.courses.authentication.shared.exceptions.BaseException;
import com.company.courses.authentication.shared.exceptions.ExceptionCode;
import org.springframework.http.HttpStatus;

public class AuthenticationDataNotFoundException extends BaseException {
    public AuthenticationDataNotFoundException(String message) {
        super(false, HttpStatus.NOT_FOUND, message, ExceptionCode.AUTH_DATA_NOT_FOUND);
    }
}
