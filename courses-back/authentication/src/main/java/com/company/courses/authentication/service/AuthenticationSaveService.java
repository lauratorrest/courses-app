package com.company.courses.authentication.service;

import com.company.courses.authentication.model.Account;
import com.company.courses.authentication.model.AuthenticatedUser;
import com.company.courses.authentication.model.AuthenticationData;
import com.company.courses.authentication.model.UserResponse;
import com.company.courses.authentication.model.enums.UserRoleEnum;
import com.company.courses.authentication.repository.AuthenticationRepository;
import com.company.courses.authentication.service.consumers.AccountsConsumer;
import com.company.courses.authentication.service.consumers.UsersConsumer;
import com.company.courses.authentication.service.jwt.JwtService;
import com.company.courses.authentication.shared.exceptions.ExceptionCode;
import com.company.courses.authentication.shared.exceptions.exceptions.RegisteredEmailException;
import com.company.courses.authentication.shared.utils.StringFixProcess;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationSaveService {

    private final AuthenticationRepository authenticationRepository;
    private final StringFixProcess stringFixProcess;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;
    private final UsersConsumer usersConsumer;
    private final AccountsConsumer accountsConsumer;
    private final JwtService jwtService;

    public AuthenticatedUser saveAuthData(String email, String password, String userName) {

        if (authenticationRepository.existsByEmail(email)) {
            throw new RegisteredEmailException(
                    messageSource.getMessage(ExceptionCode.EMAIL_REGISTERED.getType(),
                            null, LocaleContextHolder.getLocale())
            );
        }

        this.stringFixProcess.removeSpaces(password);

        return this.getAuthenticatedUserData(email, password, userName);
    }

    private AuthenticatedUser getAuthenticatedUserData(String email, String password, String userName) {
        UserResponse user = this.usersConsumer.saveUser(userName);

        AuthenticationData authenticationData = this.authenticationRepository.save(
                AuthenticationData.builder()
                        .email(email)
                        .password(passwordEncoder.encode(password))
                        .userRole(UserRoleEnum.USER)
                        .build());

        Account account = this.accountsConsumer.saveUser(authenticationData.getId(), user.getId());

        return AuthenticatedUser.builder()
                .token(this.jwtService.generateToken(user.getName(), authenticationData.getUserRole().name().toLowerCase()))
                .userEmail(email)
                .userRole(authenticationData.getUserRole())
                .accountId(account.getId())
                .accountStatus(account.getStatus())
                .accountCreatedDate(account.getCreatedDate())
                .accountUpdatedDate(account.getUpdatedDate())
                .userId(user.getId())
                .userName(user.getName())
                .build();
    }
}
