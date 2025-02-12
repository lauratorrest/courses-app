package com.company.courses.authentication.shared.exceptions;

public enum ExceptionCode {

    DIFFERENT_GIVEN_PASSWORDS("ERR-001", "Exception.DIFFERENT_GIVEN_PASSWORDS"),
    WRONG_GIVEN_PASSWORD("ERR-002", "Exception.WRONG_GIVEN_PASSWORD"),
    SAME_GIVEN_PASSWORD("ERR-003", "Exception.SAME_GIVEN_PASSWORD"),
    AUTH_DATA_NOT_FOUND("ERR-004", "Exception.AUTH_DATA_NOT_FOUND"),
    WRONG_EMAIL("ERR-005", "Exception.WRONG_EMAIL"),
    EMAIL_REGISTERED("ERR-006", "Exception.EMAIL_REGISTERED"),
    INACTIVE_ACCOUNT("ERR-007", "Exception.INACTIVE_ACCOUNT"),
    ACCOUNT_NOT_FOUND("ERR-008", "Exception.ACCOUNT_NOT_FOUND"),
    EMAIL_NOT_FOUND("ERR-009", "Exception.EMAIL_NOT_FOUND");

    private final String code;
    private final String type;

    ExceptionCode(String code, String type) {
        this.code = code;
        this.type = type;
    }

    public String getCode() {
        return this.code;
    }

    public String getType() {
        return this.type;
    }
}
