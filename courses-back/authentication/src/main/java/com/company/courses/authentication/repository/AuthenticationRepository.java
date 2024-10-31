package com.company.courses.authentication.repository;

import com.company.courses.authentication.model.Authentication;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthenticationRepository extends MongoRepository<Authentication, String> {
}
