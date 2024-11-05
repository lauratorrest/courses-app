package com.company.courses.authentication.service;

import com.company.courses.authentication.model.Authentication;
import com.company.courses.authentication.model.enums.UserRoleEnum;
import com.company.courses.authentication.repository.AuthenticationRepository;
import com.company.courses.authentication.shared.utils.StringFixProcess;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationSaveService {

    private final AuthenticationRepository authenticationRepository;
    private final StringFixProcess stringFixProcess = new StringFixProcess();
    private final PasswordEncoder passwordEncoder;

    public void saveAuthData(Authentication authentication) {
        this.stringFixProcess.removeSpaces(authentication.getPassword());
        authentication.setPassword(passwordEncoder.encode(authentication.getPassword()));
        authentication.setUserRole(UserRoleEnum.USER);
        this.authenticationRepository.save(authentication);
    }
}
