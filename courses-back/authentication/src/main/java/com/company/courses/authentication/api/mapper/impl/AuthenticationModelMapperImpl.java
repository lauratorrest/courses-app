package com.company.courses.authentication.api.mapper.impl;

import com.company.courses.authentication.api.dto.AuthenticationRequest;
import com.company.courses.authentication.api.mapper.AuthenticationModelMapper;
import com.company.courses.authentication.model.Authentication;
import com.company.courses.authentication.model.enums.UserRoleEnum;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationModelMapperImpl implements AuthenticationModelMapper {

    @Override
    public Authentication toModel(AuthenticationRequest authenticationRequest) {
        return Authentication
                .builder()
                .email(authenticationRequest.getEmail())
                .password(authenticationRequest.getPassword())
                .userRole(UserRoleEnum.USER)
                .build();
    }
}
