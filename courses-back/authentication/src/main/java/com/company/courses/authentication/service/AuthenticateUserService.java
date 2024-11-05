package com.company.courses.authentication.service;

import com.company.courses.authentication.client.AccountClient;
import com.company.courses.authentication.model.AuthenticatedUser;
import com.company.courses.authentication.model.Authentication;
import com.company.courses.authentication.model.enums.UserRoleEnum;
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
    private final AccountClient accountClient;
    private final MessageSource messageSource;

    public AuthenticatedUser authenticateUser(Authentication authentication) {
        Authentication authData = this.authenticationGetService.getAuthDataByEmail(authentication.getEmail());

        if (!passwordEncoder.matches(authentication.getPassword(), authData.getPassword())) {
            throw new WrongGivenPasswordException(
                    messageSource.getMessage(ExceptionCode.WRONG_GIVEN_PASSWORD.getType(),
                            null, LocaleContextHolder.getLocale())
            );
        }

        AuthenticatedUser authenticatedUser = accountClient.getAccountWithUserDataByAuthId(authData.getId());
        authenticatedUser.setToken("token");
        authenticatedUser.setUserEmail(authentication.getEmail());
        authenticatedUser.setUserRole(UserRoleEnum.USER);

        return authenticatedUser;
    }
}
