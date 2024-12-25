package com.company.courses.authentication.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Account {

    private String id;
    private String authId;
    private String userId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String status;
    private String type;
}
