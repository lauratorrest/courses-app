package com.company.courses.authentication.service.consumers;

import com.company.courses.authentication.model.Account;
import com.company.courses.authentication.shared.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class AccountsConsumer {
    private final RestTemplate restTemplate;

    @Autowired
    public AccountsConsumer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Account saveUser(String authId, String userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Account account = Account.builder().authId(authId).userId(userId).build();
        HttpEntity<Account> accountRequestEntity = new HttpEntity<>(account, headers);

        URI uri = UriComponentsBuilder.fromHttpUrl(AppUtil.ACCOUNTS_PATH + "/Save")
                .build()
                .encode()
                .toUri();
        ResponseEntity<Account> response = this.restTemplate.postForEntity(uri, accountRequestEntity, Account.class);

        return response.getBody();
    }
}
