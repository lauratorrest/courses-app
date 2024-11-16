package com.company.courses.authentication.api.mapper.impl;

import com.company.courses.authentication.api.dto.AuthenticationRequest;
import com.company.courses.authentication.api.mapper.AuthenticationModelMapper;
import com.company.courses.authentication.model.AuthenticationData;
import com.company.courses.authentication.model.enums.UserRoleEnum;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationModelMapperImpl implements AuthenticationModelMapper {

    @Override
    public AuthenticationData toModel(AuthenticationRequest authenticationRequest) {
        return AuthenticationData
                .builder()
                .email(authenticationRequest.getEmail())
                .password(authenticationRequest.getPassword())
                .userRole(UserRoleEnum.USER)
                .build();
    }
}
