package com.company.courses.authentication.service.microservices;

import com.company.courses.authentication.model.User;
import com.company.courses.authentication.shared.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class UsersConsumer {

    private final RestTemplate restTemplate;

    @Autowired
    public UsersConsumer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public User saveUser(String userName) {
        URI uri = UriComponentsBuilder.fromHttpUrl(AppUtil.USERS_PATH)
                .queryParam("userName", userName)
                .build()
                .encode()
                .toUri();
        ResponseEntity<User> response = this.restTemplate.postForEntity(uri, null, User.class);

        return response.getBody();
    }
}
