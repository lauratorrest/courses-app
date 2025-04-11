package com.company.courses.authentication.service.authentication;

import com.company.courses.authentication.client.consumers.UsersConsumer;
import com.company.courses.authentication.model.Account;
import com.company.courses.authentication.model.AuthenticatedUser;
import com.company.courses.authentication.model.AuthenticationData;
import com.company.courses.authentication.model.UserResponse;
import com.company.courses.authentication.model.enums.AccountStatusEnum;
import com.company.courses.authentication.service.account.AccountGetService;
import com.company.courses.authentication.service.authentication.jwt.JwtService;
import com.company.courses.authentication.shared.exceptions.ExceptionCode;
import com.company.courses.authentication.shared.exceptions.exceptions.InactiveAccountException;
import com.company.courses.authentication.shared.exceptions.exceptions.WrongGivenPasswordException;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class AuthenticateUserService {

    private final AuthenticationGetService authenticationGetService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;
    private final AccountGetService accountGetService;
    private final UsersConsumer usersConsumer;

    public AuthenticateUserService(AuthenticationGetService authenticationGetService,
                                   AuthenticationManager authenticationManager,
                                   JwtService jwtService,
                                   PasswordEncoder passwordEncoder,
                                   MessageSource messageSource,
                                   AccountGetService accountGetService,
                                   UsersConsumer usersConsumer) {
        this.authenticationGetService = authenticationGetService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.messageSource = messageSource;
        this.accountGetService = accountGetService;
        this.usersConsumer = usersConsumer;
    }

    public AuthenticatedUser authenticateUser(AuthenticationData authenticationData) {
        AuthenticationData existingAuthData = this.authenticationGetService.getAuthDataByEmail(authenticationData.getEmail());

        if (!passwordEncoder.matches(authenticationData.getPassword(), existingAuthData.getPassword())) {
            throw new WrongGivenPasswordException(
                    messageSource.getMessage(ExceptionCode.WRONG_GIVEN_PASSWORD.getType(),
                            null, LocaleContextHolder.getLocale())
            );
        }

        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(existingAuthData.getEmail(), authenticationData.getPassword())
        );

        if (authentication.isAuthenticated()) {
            return this.buildAuthenticatedUserData(existingAuthData);
        }

        return null;
    }

    @NotNull
    private AuthenticatedUser buildAuthenticatedUserData(AuthenticationData existingAuthData) {
        Account account = this.accountGetService.getByAuthId(existingAuthData.getId());
        UserResponse user = this.usersConsumer.getUserInfo(account.getUserId());
        AuthenticatedUser authenticatedUser = new AuthenticatedUser(account, user);

        if (!Objects.equals(authenticatedUser.getAccountStatus(), AccountStatusEnum.ACTIVE)) {
            throw new InactiveAccountException(
                    messageSource.getMessage(ExceptionCode.INACTIVE_ACCOUNT.getType(),
                            null, LocaleContextHolder.getLocale())
            );
        } else {
            authenticatedUser.setUserEmail(existingAuthData.getEmail());
            String token = this.jwtService.generateToken(authenticatedUser.getUserEmail(),
                    existingAuthData.getUserRole().name().toLowerCase());
            authenticatedUser.setToken(token);
            authenticatedUser.setUserRole(existingAuthData.getUserRole());
            return authenticatedUser;
        }
    }

    public Boolean validateToken(String token, String email) {
        String cleanToken = token.replace("Bearer ", "");
        UserDetails user = new User(email, "", new ArrayList<>());
        return this.jwtService.validateToken(cleanToken, user);
    }
}
