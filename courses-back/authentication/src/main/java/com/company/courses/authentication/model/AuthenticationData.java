package com.company.courses.authentication.model;

import com.company.courses.authentication.model.enums.UserRoleEnum;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("authentication")
public class AuthenticationData {

    private String id;
    private String email;
    private String password;
    private UserRoleEnum userRole;
    private LocalDateTime passwordUpdatedDate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public AuthenticationData(String email, String password, UserRoleEnum userRole) {
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.createdDate = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getPasswordUpdatedDate() {
        return passwordUpdatedDate;
    }

    public void setPasswordUpdatedDate() {
        this.passwordUpdatedDate = LocalDateTime.now();
    }
}
