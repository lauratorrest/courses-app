package com.company.courses.authentication.repository;

import com.company.courses.authentication.model.AuthenticationData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthenticationRepository extends MongoRepository<AuthenticationData, String> {

    Optional<AuthenticationData> findByEmail(String email);

    boolean existsByEmail(String email);
}
