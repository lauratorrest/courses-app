package com.company.courses.authentication.service;

import com.company.courses.authentication.model.AuthenticationData;
import com.company.courses.authentication.model.User;
import com.company.courses.authentication.model.enums.UserRoleEnum;
import com.company.courses.authentication.repository.AuthenticationRepository;
import com.company.courses.authentication.service.consumers.UsersConsumer;
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

    public AuthenticationData saveAuthData(String email, String password, String userName) {

        if (authenticationRepository.existsByEmail(email)) {
            throw new RegisteredEmailException(
                    messageSource.getMessage(ExceptionCode.EMAIL_REGISTERED.getType(),
                            null, LocaleContextHolder.getLocale())
            );
        }

        this.stringFixProcess.removeSpaces(password);

        User userResponse = this.usersConsumer.saveUser(userName);
        AuthenticationData authenticationData = this.authenticationRepository.save(
                AuthenticationData.builder()
                        .email(email)
                        .password(passwordEncoder.encode(password))
                        .userRole(UserRoleEnum.USER)
                        .build());

        //SAVE USER
        //SAVE ACCOUNT WITH USER AND AUTH
        return authenticationData;
    }
}
