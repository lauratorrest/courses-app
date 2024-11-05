package com.company.courses.authentication.shared.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    DIFFERENT_GIVEN_PASSWORDS("ERR-001", "Exception.DIFFERENT_GIVEN_PASSWORDS"),
    WRONG_GIVEN_PASSWORD("ERR-002", "Exception.WRONG_GIVEN_PASSWORD"),
    SAME_GIVEN_PASSWORD("ERR-003", "Exception.SAME_GIVEN_PASSWORD"),
    AUTH_DATA_NOT_FOUND("ERR-004", "Exception.AUTH_DATA_NOT_FOUND"),
    WRONG_EMAIL("ERR-005", "Exception.WRONG_EMAIL");

    private final String code;
    private final String type;

    ExceptionCode(String code, String type) {
        this.code = code;
        this.type = type;
    }
}
