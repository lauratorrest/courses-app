package com.company.courses.authentication.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Document("authentication")
public class Authentication {

    private String id;
    @Getter
    @Setter
    private String password;
    private LocalDateTime passwordUpdatedDate;

    public void setPasswordUpdatedDate() {
        this.passwordUpdatedDate = LocalDateTime.now();
    }
}
