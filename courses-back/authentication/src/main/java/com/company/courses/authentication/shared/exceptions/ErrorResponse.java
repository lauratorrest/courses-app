package com.company.courses.authentication.shared.exceptions;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ErrorResponse {

    private HttpStatus httpStatus;
    private String code;
    private String message;
    private String date;
}
