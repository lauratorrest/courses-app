package com.company.courses.authentication.shared.exceptions.exceptions;

import com.company.courses.authentication.shared.exceptions.BaseException;
import com.company.courses.authentication.shared.exceptions.ExceptionCode;
import org.springframework.http.HttpStatus;

public class RegisteredEmailException extends BaseException {

    public RegisteredEmailException(String message) {
        super(false, HttpStatus.CONFLICT, message, ExceptionCode.EMAIL_REGISTERED);
    }
}
