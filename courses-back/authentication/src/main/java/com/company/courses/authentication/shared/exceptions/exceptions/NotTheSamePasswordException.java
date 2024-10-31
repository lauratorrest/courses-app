package com.company.courses.authentication.shared.exceptions.exceptions;

import com.company.courses.authentication.shared.exceptions.BaseException;
import com.company.courses.authentication.shared.exceptions.ExceptionCode;
import org.springframework.http.HttpStatus;

public class NotTheSamePasswordException extends BaseException {

    public NotTheSamePasswordException(String message) {
        super(false, HttpStatus.CONFLICT, message, ExceptionCode.DIFFERENT_GIVEN_PASSWORDS);
    }
}
