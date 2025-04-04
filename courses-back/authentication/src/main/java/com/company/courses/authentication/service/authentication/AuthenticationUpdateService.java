package com.company.courses.authentication.service.authentication;

import com.company.courses.authentication.model.AuthenticationData;
import com.company.courses.authentication.repository.AuthenticationRepository;
import com.company.courses.authentication.shared.exceptions.ExceptionCode;
import com.company.courses.authentication.shared.exceptions.exceptions.NotTheSamePasswordException;
import com.company.courses.authentication.shared.exceptions.exceptions.SameGivenPasswordException;
import com.company.courses.authentication.shared.exceptions.exceptions.WrongGivenPasswordException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationUpdateService {

    private final AuthenticationRepository authenticationRepository;
    private final AuthenticationGetService authenticationGetService;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;

    public AuthenticationUpdateService(AuthenticationRepository authenticationRepository,
                                       AuthenticationGetService authenticationGetService,
                                       PasswordEncoder passwordEncoder,
                                       MessageSource messageSource) {
        this.authenticationRepository = authenticationRepository;
        this.authenticationGetService = authenticationGetService;
        this.passwordEncoder = passwordEncoder;
        this.messageSource = messageSource;
    }

    public void updateAccountPassword(
            String email,
            String currentPassword,
            String newPassword1,
            String newPassword2) {

        AuthenticationData currentAuthData = this.authenticationGetService.getAuthDataByEmail(email);

        this.sameGivenAndCurrentPasswordValidation(currentPassword, newPassword1);
        this.equalsPasswordsValidation(newPassword1, newPassword2);

        this.correctGivenPasswordValidation(currentPassword, currentAuthData.getPassword());

        currentAuthData.setPassword(passwordEncoder.encode(newPassword1));
        currentAuthData.setPasswordUpdatedDate();
        this.authenticationRepository.save(currentAuthData);
    }

    private void sameGivenAndCurrentPasswordValidation(String currentPassword, String newPassword1) {
        if (Objects.equals(currentPassword, newPassword1)) {
            throw new SameGivenPasswordException(
                    messageSource.getMessage(
                            ExceptionCode.SAME_GIVEN_PASSWORD.getType(),
                            null, LocaleContextHolder.getLocale()
                    )
            );
        }
    }

    private void equalsPasswordsValidation(String newPassword1, String newPassword2) {
        if (!Objects.equals(newPassword1, newPassword2)) {
            throw new NotTheSamePasswordException(
                    messageSource.getMessage(
                            ExceptionCode.DIFFERENT_GIVEN_PASSWORDS.getType(),
                            null, LocaleContextHolder.getLocale()
                    )
            );
        }
    }

    private void correctGivenPasswordValidation(String currentPassword, String password) {
        if (!passwordEncoder.matches(currentPassword, password)) {
            throw new WrongGivenPasswordException(
                    messageSource.getMessage(
                            ExceptionCode.WRONG_GIVEN_PASSWORD.getType(),
                            null, LocaleContextHolder.getLocale()
                    )
            );
        }
    }

}
