package com.company.courses.users.shared.exceptions.exceptions;

import com.company.courses.users.shared.exceptions.BaseException;
import com.company.courses.users.shared.exceptions.ExceptionCode;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException(String message) {
        super(false, HttpStatus.NOT_FOUND, message, ExceptionCode.USER_NOT_FOUND);
    }
}
