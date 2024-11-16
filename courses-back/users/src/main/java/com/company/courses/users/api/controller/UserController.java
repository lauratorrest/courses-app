package com.company.courses.users.api.controller;

import com.company.courses.users.api.dto.UserUpdateRequest;
import com.company.courses.users.api.mapper.UserModelMapper;
import com.company.courses.users.model.User;
import com.company.courses.users.service.UserGetService;
import com.company.courses.users.service.UserSaveService;
import com.company.courses.users.service.UserUpdateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Tag(name = "Users API", description = "API for users management")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserSaveService userSaveService;
    private final UserUpdateService userUpdateService;
    private final UserGetService userGetService;
    private final UserModelMapper userModelMapper;

    @Operation(summary = "Create a new user")
    @PostMapping("/save/{userName}")
    public ResponseEntity<User> saveUser(@PathVariable("userName") String userName) {
        return ResponseEntity.ok(this.userSaveService.saveNewUser(userName));
    }

    @Operation(summary = "Update existing user")
    @PutMapping("/update-info")
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        return ResponseEntity.ok(this.userUpdateService.updateUserInformation(this.userModelMapper.toUserModel(userUpdateRequest)));
    }

    @Operation(summary = "Get user information by id")
    @GetMapping("/info/{userId}")
    public ResponseEntity<User> getUserInfo(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(this.userGetService.getUserInformation(userId));
    }
}
