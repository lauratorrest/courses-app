package com.company.courses.authentication.shared.exceptions.exceptions;

import com.company.courses.authentication.shared.exceptions.BaseException;
import com.company.courses.authentication.shared.exceptions.ExceptionCode;
import org.springframework.http.HttpStatus;

public class InactiveAccountException extends BaseException {

    public InactiveAccountException(String message) {
        super(false, HttpStatus.CONFLICT, message, ExceptionCode.INACTIVE_ACCOUNT);
    }
}
