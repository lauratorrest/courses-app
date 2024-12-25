package com.company.courses.authentication.service.consumers;

import com.company.courses.authentication.model.UserResponse;
import com.company.courses.authentication.shared.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class UsersConsumer {

    public static final String USER_NAME = "userName";
    private final RestTemplate restTemplate;

    @Autowired
    public UsersConsumer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserResponse saveUser(String userName) {
        URI uri = UriComponentsBuilder.fromHttpUrl(AppUtil.USERS_PATH + "/save")
                .queryParam(USER_NAME, userName)
                .build()
                .encode()
                .toUri();
        ResponseEntity<UserResponse> response = this.restTemplate.postForEntity(uri, null, UserResponse.class);

        return response.getBody();
    }
}
