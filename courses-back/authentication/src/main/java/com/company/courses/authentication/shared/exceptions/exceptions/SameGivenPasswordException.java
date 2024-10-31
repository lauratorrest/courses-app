package com.company.courses.authentication.shared.exceptions.exceptions;

import com.company.courses.authentication.shared.exceptions.BaseException;
import com.company.courses.authentication.shared.exceptions.ExceptionCode;
import org.springframework.http.HttpStatus;

public class SameGivenPasswordException extends BaseException {
    public SameGivenPasswordException(String message) {
        super(false, HttpStatus.CONFLICT, message, ExceptionCode.SAME_GIVEN_PASSWORD);
    }
}
