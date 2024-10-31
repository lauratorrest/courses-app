package com.company.courses.users.service;

import com.company.courses.users.model.User;
import com.company.courses.users.repository.UserRepository;
import com.company.courses.users.shared.exceptions.ExceptionCode;
import com.company.courses.users.shared.exceptions.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserGetService {

    private final UserRepository userRepository;
    private final MessageSource messageSource;

    public User getUserInformation(String id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new UserNotFoundException(
                messageSource.getMessage(ExceptionCode.USER_NOT_FOUND.getType(),
                        null, LocaleContextHolder.getLocale())
        ));
    }
}
