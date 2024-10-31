package com.company.courses.users.api.mapper;

import com.company.courses.users.api.dto.UserUpdateRequest;
import com.company.courses.users.model.User;

public interface UserModelMapper {

    User toUserModel(UserUpdateRequest userUpdateRequest);
}
