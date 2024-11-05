package com.company.courses.authentication.model;

import com.company.courses.authentication.model.enums.UserRoleEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Document("authentication")
public class Authentication {

    private String id;
    private String email;
    private String password;
    private UserRoleEnum userRole;
    private LocalDateTime passwordUpdatedDate;

    public void setPasswordUpdatedDate() {
        this.passwordUpdatedDate = LocalDateTime.now();
    }
}
