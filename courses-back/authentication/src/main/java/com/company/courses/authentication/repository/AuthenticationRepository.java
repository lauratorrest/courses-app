package com.company.courses.authentication.repository;

import com.company.courses.authentication.model.Authentication;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthenticationRepository extends MongoRepository<Authentication, String> {

    Optional<Authentication> findByEmail(String email);
}
