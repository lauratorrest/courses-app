package com.company.courses.authentication.service;

import com.company.courses.authentication.model.AuthenticationData;
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

    public void saveAuthData(AuthenticationData authenticationData) {
        //repeated email exception
        this.stringFixProcess.removeSpaces(authenticationData.getPassword());
        authenticationData.setPassword(passwordEncoder.encode(authenticationData.getPassword()));
        authenticationData.setUserRole(UserRoleEnum.USER);
        this.authenticationRepository.save(authenticationData);
    }
}
