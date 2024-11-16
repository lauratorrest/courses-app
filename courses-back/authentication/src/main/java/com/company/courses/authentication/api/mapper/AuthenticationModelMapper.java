package com.company.courses.authentication.api.mapper;

import com.company.courses.authentication.api.dto.AuthenticationRequest;
import com.company.courses.authentication.model.AuthenticationData;

public interface AuthenticationModelMapper {

    AuthenticationData toModel(AuthenticationRequest authenticationRequest);
}
