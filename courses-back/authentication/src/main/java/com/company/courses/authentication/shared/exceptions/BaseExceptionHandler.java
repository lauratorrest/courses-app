package com.company.courses.authentication.shared.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class BaseExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> errorHandler(BaseException baseException) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .httpStatus(baseException.getStatus())
                .code(baseException.getCode())
                .message(baseException.getMessage())
                .date(baseException.getDate().toString())
                .build(), HttpStatusCode.valueOf(baseException.getStatus().value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errors.append(fieldError.getDefaultMessage()).append(", "));

        // Eliminar la última coma y espacio extra
        if (!errors.isEmpty()) {
            errors.setLength(errors.length() - 2);
        }

        return new ResponseEntity<>(
                ErrorResponse.builder().httpStatus(HttpStatus.BAD_REQUEST).code("VALIDATIONS_ERROR")
                        .message(errors.toString()).date(LocalDate.now().toString()).build(),
                HttpStatus.BAD_REQUEST);
    }
}
