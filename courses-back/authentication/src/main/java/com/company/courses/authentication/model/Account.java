package com.company.courses.authentication.model;

import com.company.courses.authentication.model.enums.AccountStatusEnum;
import com.company.courses.authentication.model.enums.AccountTypeEnum;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("accounts")
public class Account {

    private String id;
    private final String authId;
    private final String userId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private final AccountStatusEnum status;
    private AccountTypeEnum type;

    public Account(String authId, String userId, AccountTypeEnum type) {
        this.authId = authId;
        this.userId = userId;
        this.createdDate = LocalDateTime.now();
        this.status = AccountStatusEnum.ACTIVE;
        this.type = type;
        this.createdDate = LocalDateTime.now();
    }

    public String getId() {
        return this.id;
    }

    public String getUserId() {
        return this.userId;
    }

    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return this.updatedDate;
    }

    public AccountStatusEnum getStatus() {
        return this.status;
    }

    public void setType(AccountTypeEnum type) {
        this.type = type;
    }
}
