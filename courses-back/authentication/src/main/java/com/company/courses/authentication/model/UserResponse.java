package com.company.courses.authentication.model;

import java.util.List;

public class UserResponse {

    private String id;
    private String name;
    private String profilePictureUrl;
    private String profession;
    private List<String> madeCoursesIds;
    private List<String> boughtCoursesIds;
    private List<String> cartCoursesIds;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public String getProfession() {
        return profession;
    }

    public List<String> getMadeCoursesIds() {
        return madeCoursesIds;
    }

    public List<String> getBoughtCoursesIds() {
        return boughtCoursesIds;
    }

    public List<String> getCartCoursesIds() {
        return cartCoursesIds;
    }
}
