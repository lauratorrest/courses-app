package com.company.courses.authentication.service;

import com.company.courses.authentication.model.Authentication;
import com.company.courses.authentication.repository.AuthenticationRepository;
import com.company.courses.authentication.shared.exceptions.ExceptionCode;
import com.company.courses.authentication.shared.exceptions.exceptions.NotTheSamePasswordException;
import com.company.courses.authentication.shared.exceptions.exceptions.SameGivenPasswordException;
import com.company.courses.authentication.shared.exceptions.exceptions.WrongGivenPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AuthenticationUpdateService {

    private final AuthenticationRepository authenticationRepository;
    private final AuthenticationGetService authenticationGetService;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;

    public void updateAccountPassword(
            String authId,
            String currentPassword,
            String newPassword1,
            String newPassword2) {

        this.sameGivenAndCurrentPasswordValidation(currentPassword, newPassword1);
        this.equalsPasswordsValidation(newPassword1, newPassword2);

        Authentication currentAuthData = this.authenticationGetService.getAuthenticationData(authId);
        this.correctGivenPasswordValidation(currentPassword, currentAuthData.getPassword());

        currentAuthData.setPassword(passwordEncoder.encode(newPassword1));
        currentAuthData.setPasswordUpdatedDate();
        this.authenticationRepository.save(currentAuthData);
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
