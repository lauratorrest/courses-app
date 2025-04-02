package com.company.courses.authentication.client.consumers;

import com.company.courses.authentication.model.UserResponse;

public interface UsersConsumer {

    UserResponse saveUser(String userName);

    UserResponse getUserInfo(String userId);
}
