package com.company.courses.authentication.shared.exceptions.exceptions;

import com.company.courses.authentication.shared.exceptions.BaseException;
import com.company.courses.authentication.shared.exceptions.ExceptionCode;
import org.springframework.http.HttpStatus;

public class WrongGivenPasswordException extends BaseException {

    public WrongGivenPasswordException(String message) {
        super(false, HttpStatus.CONFLICT, message, ExceptionCode.WRONG_GIVEN_PASSWORD);
    }
}
