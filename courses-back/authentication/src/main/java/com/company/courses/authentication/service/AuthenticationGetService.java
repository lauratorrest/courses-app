package com.company.courses.authentication.service;

import com.company.courses.authentication.model.Authentication;
import com.company.courses.authentication.repository.AuthenticationRepository;
import com.company.courses.authentication.shared.exceptions.ExceptionCode;
import com.company.courses.authentication.shared.exceptions.exceptions.EmailNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthenticationGetService {

    private final AuthenticationRepository authenticationRepository;
    private final MessageSource messageSource;

    public Authentication getAuthDataByEmail(String email) {
        Optional<Authentication> authData = authenticationRepository.findByEmail(email);
        return authData.orElseThrow(() -> new EmailNotFoundException(
                messageSource.getMessage(ExceptionCode.WRONG_EMAIL.getType(),
                        null, LocaleContextHolder.getLocale())
        ));
    }
}
