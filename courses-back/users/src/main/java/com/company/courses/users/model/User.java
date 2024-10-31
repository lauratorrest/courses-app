package com.company.courses.users.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@Builder
@Document("users")
public class User {

    private String id;
    private String name;
    private String webPageUrl;
    private String linkedInUrl;
    private String youtubeChannelUrl;
    private String facebookUrl;
    private String instagramUrl;
    private String profilePictureUrl;
    private String profession;
    private String aboutMe;
    private List<String> madeCoursesIds;
    private List<String> boughtCoursesIds;
    private List<String> cartCoursesIds;
}
