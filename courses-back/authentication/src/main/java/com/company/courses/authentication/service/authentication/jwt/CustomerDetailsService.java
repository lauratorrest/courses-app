package com.company.courses.authentication.service.authentication.jwt;

import com.company.courses.authentication.model.AuthenticationData;
import com.company.courses.authentication.service.authentication.AuthenticationGetService;
import com.company.courses.authentication.shared.exceptions.ExceptionCode;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class CustomerDetailsService implements UserDetailsService {

    private final AuthenticationGetService authenticationGetService;
    private final MessageSource messageSource;

    public CustomerDetailsService(AuthenticationGetService authenticationGetService,
                                  MessageSource messageSource) {
        this.authenticationGetService = authenticationGetService;
        this.messageSource = messageSource;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenticationData authenticationData = this.authenticationGetService.getAuthDataByEmail(username);

        if (Objects.nonNull(authenticationData)) {
            return new User(authenticationData.getEmail(), authenticationData.getPassword(), new ArrayList<>());
        }

        throw new UsernameNotFoundException(
                messageSource.getMessage(
                        ExceptionCode.EMAIL_NOT_FOUND.getType(),
                        null,
                        LocaleContextHolder.getLocale()
                )
        );
    }

}
