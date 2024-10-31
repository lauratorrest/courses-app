package com.company.courses.users.service;

import com.company.courses.users.model.User;
import com.company.courses.users.repository.UserRepository;
import com.company.courses.users.shared.utils.StringFixProcess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserSaveService {

    private final UserRepository userRepository;
    private final StringFixProcess stringFixProcess;

    public User saveNewUser(String name) {
        stringFixProcess.fixUserName(name);
        return this.userRepository.save(User.builder().name(name).build());
    }
}
