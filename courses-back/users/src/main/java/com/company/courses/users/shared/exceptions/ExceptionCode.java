package com.company.courses.users.shared.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    USER_NOT_FOUND("ERR-001", "Exception.USER_NOT_FOUND");

    private final String code;
    private final String type;

    ExceptionCode(String code, String type) {
        this.code = code;
        this.type = type;
    }
}
