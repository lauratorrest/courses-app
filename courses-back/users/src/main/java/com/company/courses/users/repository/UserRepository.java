package com.company.courses.users.repository;

import com.company.courses.users.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
