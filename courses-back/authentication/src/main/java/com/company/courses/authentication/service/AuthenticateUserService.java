package com.company.courses.authentication.service;

import com.company.courses.authentication.client.AccountClient;
import com.company.courses.authentication.model.AuthenticatedUser;
import com.company.courses.authentication.model.AuthenticationData;
import com.company.courses.authentication.service.jwt.CustomerDetailsService;
import com.company.courses.authentication.service.jwt.JwtService;
import com.company.courses.authentication.shared.exceptions.ExceptionCode;
import com.company.courses.authentication.shared.exceptions.exceptions.InactiveAccountException;
import com.company.courses.authentication.shared.exceptions.exceptions.WrongGivenPasswordException;
import com.company.courses.authentication.shared.utils.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AuthenticateUserService {

    private final AuthenticationGetService authenticationGetService;
    private final AuthenticationManager authenticationManager;
    private final CustomerDetailsService customerDetailsService; //DO NOT DELETE
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AccountClient accountClient;
    private final MessageSource messageSource;

    public AuthenticatedUser authenticateUser(AuthenticationData authenticationData) {
        AuthenticationData existingAuthData = this.authenticationGetService.getAuthDataByEmail(authenticationData.getEmail());

        if (!passwordEncoder.matches(authenticationData.getPassword(), existingAuthData.getPassword())) {
            throw new WrongGivenPasswordException(
                    messageSource.getMessage(ExceptionCode.WRONG_GIVEN_PASSWORD.getType(),
                            null, LocaleContextHolder.getLocale())
            );
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(existingAuthData.getEmail(), authenticationData.getPassword())
        );

        if (authentication.isAuthenticated()) {
            AuthenticatedUser authenticatedUser = this.accountClient.getAccountWithUserDataByAuthId(existingAuthData.getId());

            if (Objects.equals(authenticatedUser.getAccountStatus(), AppUtil.ACTIVE_STATUS)) {
                authenticatedUser.setUserRole(existingAuthData.getUserRole());
                authenticatedUser.setUserEmail(existingAuthData.getEmail());
                authenticatedUser.setToken(this.jwtService.generateToken(authenticatedUser.getUserEmail(), authenticatedUser.getUserRole().name().toLowerCase()));
                return authenticatedUser;
            } else {
                throw new InactiveAccountException(
                        messageSource.getMessage(ExceptionCode.INACTIVE_ACCOUNT.getType(),
                                null, LocaleContextHolder.getLocale())
                );
            }
        }

        return null;
    }
}
