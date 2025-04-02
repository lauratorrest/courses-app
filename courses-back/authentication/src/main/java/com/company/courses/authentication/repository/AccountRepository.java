package com.company.courses.authentication.repository;

import com.company.courses.authentication.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, String> {
    Optional<Account> findByAuthId(String authId);
}
