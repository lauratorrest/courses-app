package com.company.courses.authentication.shared.exceptions;

import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class BaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -822690113517284691L;

    private final boolean retryable;
    private final HttpStatus status;
    private final String code;
    private final String message;
    private final LocalDateTime date;
    private final ExceptionCode exceptionCode;
    private final String id = UUID.randomUUID().toString();

    protected BaseException(boolean retryable, HttpStatus status, String message,
                            ExceptionCode exceptionCode) {
        this.retryable = retryable;
        this.code = exceptionCode.getCode();
        this.message = message;
        this.status = status;
        this.exceptionCode = exceptionCode;
        this.date = LocalDateTime.now(ZoneId.systemDefault());
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
