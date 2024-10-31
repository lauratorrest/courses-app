package com.company.courses.authentication.service;

import com.company.courses.authentication.model.Authentication;
import com.company.courses.authentication.shared.exceptions.ExceptionCode;
import com.company.courses.authentication.shared.exceptions.exceptions.WrongGivenPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticateUserService {

    private final AuthenticationGetService authenticationGetService;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;

    public String authenticateUser(String authId, String givenPassword) {
        Authentication authData = this.authenticationGetService.getAuthenticationData(authId);

        if (!passwordEncoder.matches(givenPassword, authData.getPassword())) {
            throw new WrongGivenPasswordException(
                    messageSource.getMessage(ExceptionCode.WRONG_GIVEN_PASSWORD.getType(),
                            null, LocaleContextHolder.getLocale())
            );
        }

        return "given token";
    }
}
