package com.company.courses.authentication.client.consumers.impl;

import com.company.courses.authentication.client.UserClient;
import com.company.courses.authentication.client.consumers.UsersConsumer;
import com.company.courses.authentication.model.UserResponse;
import com.company.courses.authentication.shared.utils.AppUtil;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class UsersConsumerImpl implements UsersConsumer {

    private final UserClient userClient;

    public UsersConsumerImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppUtil.USERS_PATH + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.userClient = retrofit.create(UserClient.class);
    }

    public UserResponse saveUser(String userName) {
        try {
            Call<UserResponse> call = this.userClient.saveUser(userName);
            Response<UserResponse> response = call.execute();
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserResponse getUserInfo(String userId) {
        try {
            Call<UserResponse> call = this.userClient.getUserInfo(userId);
            Response<UserResponse> response = call.execute();
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
