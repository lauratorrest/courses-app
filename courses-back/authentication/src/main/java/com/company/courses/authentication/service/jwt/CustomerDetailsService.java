package com.company.courses.authentication.service.jwt;

import com.company.courses.authentication.client.AccountClient;
import com.company.courses.authentication.model.AuthenticatedUser;
import com.company.courses.authentication.model.AuthenticationData;
import com.company.courses.authentication.service.AuthenticationGetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomerDetailsService implements UserDetailsService {

    private final AuthenticationGetService authenticationGetService;
    private final AccountClient accountClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenticationData authenticationData = this.authenticationGetService.getAuthDataByEmail(username);

        if (Objects.nonNull(authenticationData)) {
            AuthenticatedUser authenticatedUser = this.accountClient.getAccountWithUserDataByAuthId(authenticationData.getId());
            return new User(authenticatedUser.getUserName(), authenticationData.getPassword(), new ArrayList<>());
        }

        throw new UsernameNotFoundException("User not found");
    }

}
