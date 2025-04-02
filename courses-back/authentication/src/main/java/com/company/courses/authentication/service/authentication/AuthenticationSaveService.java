package com.company.courses.authentication.service.authentication;

import com.company.courses.authentication.client.consumers.UsersConsumer;
import com.company.courses.authentication.model.Account;
import com.company.courses.authentication.model.AuthenticatedUser;
import com.company.courses.authentication.model.AuthenticationData;
import com.company.courses.authentication.model.UserResponse;
import com.company.courses.authentication.model.enums.UserRoleEnum;
import com.company.courses.authentication.repository.AuthenticationRepository;
import com.company.courses.authentication.service.account.AccountSaveService;
import com.company.courses.authentication.service.authentication.jwt.JwtService;
import com.company.courses.authentication.shared.exceptions.ExceptionCode;
import com.company.courses.authentication.shared.exceptions.exceptions.RegisteredEmailException;
import com.company.courses.authentication.shared.utils.StringFixProcess;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationSaveService {

    private final AuthenticationRepository authenticationRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;
    private final UsersConsumer usersConsumer;
    private final AccountSaveService accountSaveService;
    private final JwtService jwtService;

    public AuthenticationSaveService(AuthenticationRepository authenticationRepository,
                                     PasswordEncoder passwordEncoder,
                                     MessageSource messageSource,
                                     UsersConsumer usersConsumer,
                                     AccountSaveService accountSaveService,
                                     JwtService jwtService) {
        this.authenticationRepository = authenticationRepository;
        this.passwordEncoder = passwordEncoder;
        this.messageSource = messageSource;
        this.usersConsumer = usersConsumer;
        this.accountSaveService = accountSaveService;
        this.jwtService = jwtService;
    }

    public AuthenticatedUser saveAuthData(String email, String password, String userName) {

        if (authenticationRepository.existsByEmail(email)) {
            throw new RegisteredEmailException(
                    messageSource.getMessage(ExceptionCode.EMAIL_REGISTERED.getType(),
                            null, LocaleContextHolder.getLocale())
            );
        }

        StringFixProcess.removeSpaces(password);

        UserResponse user = this.usersConsumer.saveUser(userName); //Save User
        AuthenticationData authenticationData = this.authenticationRepository.save(
                new AuthenticationData(email, this.passwordEncoder.encode(password), UserRoleEnum.USER)); //Save Data for Authentication
        Account account = this.accountSaveService.saveUser(authenticationData.getId(), user.getId()); //Save Account related to User and Auth

        return this.getAuthenticatedUserData(user, authenticationData, account);
    }

    private AuthenticatedUser getAuthenticatedUserData(UserResponse user,
                                                       AuthenticationData authenticationData,
                                                       Account account) {

        String token = this.jwtService.generateToken(user.getName(), authenticationData.getUserRole().name().toLowerCase());
        return new AuthenticatedUser(
                token,
                authenticationData.getEmail(),
                authenticationData.getUserRole(),
                account.getId(),
                account.getStatus(),
                account.getCreatedDate(),
                account.getUpdatedDate(),
                user.getId(),
                user.getName()
        );
    }
}
