package com.company.courses.authentication.service;

import com.company.courses.authentication.model.Authentication;
import com.company.courses.authentication.repository.AuthenticationRepository;
import com.company.courses.authentication.shared.exceptions.ExceptionCode;
import com.company.courses.authentication.shared.exceptions.exceptions.AuthenticationDataNotFoundException;
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

    public Authentication getAuthenticationData(String authId){
        Optional<Authentication> authData = authenticationRepository.findById(authId);
        return authData.orElseThrow(() -> new AuthenticationDataNotFoundException(
                messageSource.getMessage(ExceptionCode.AUTH_DATA_NOT_FOUND.getType(),
                        null, LocaleContextHolder.getLocale())
        ));
    }
}
