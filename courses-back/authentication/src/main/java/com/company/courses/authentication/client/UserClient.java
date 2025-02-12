package com.company.courses.authentication.client;

import com.company.courses.authentication.model.UserResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserClient {

    @POST("save")
    Call<UserResponse> saveUser(@Query("userName") String userName);

    @GET("info/{userId}")
    Call<UserResponse> getUserInfo(@Path("userId") String userId);
}
