package com.company.courses.authentication.shared.exceptions.exceptions;

import com.company.courses.authentication.shared.exceptions.BaseException;
import com.company.courses.authentication.shared.exceptions.ExceptionCode;
import org.springframework.http.HttpStatus;

public class EmailNotFoundException extends BaseException {
    public EmailNotFoundException(String message) {
        super(false, HttpStatus.NOT_FOUND, message, ExceptionCode.WRONG_EMAIL);
    }
}
