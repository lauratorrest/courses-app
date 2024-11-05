package com.company.courses.authentication.api.mapper;

import com.company.courses.authentication.api.dto.AuthenticationRequest;
import com.company.courses.authentication.model.Authentication;

public interface AuthenticationModelMapper {

    Authentication toModel(AuthenticationRequest authenticationRequest);
}
