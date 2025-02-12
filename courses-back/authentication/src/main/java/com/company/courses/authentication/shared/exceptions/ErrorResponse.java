package com.company.courses.authentication.shared.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    private final String date;

    public ErrorResponse(HttpStatus httpStatus, String code, String message, String date) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }
}
