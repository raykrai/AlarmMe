package com.escacena.alarmme.repository;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.escacena.alarmme.client.AlarmMeAPI;
import com.escacena.alarmme.common.MyApp;
import com.escacena.alarmme.response.ResponsePicture;
import com.escacena.alarmme.response.ResponseUser;
import com.escacena.alarmme.service.ServiceAlarmMeAPI;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private ServiceAlarmMeAPI service;
    private MutableLiveData<ResponseUser> user = new MutableLiveData<>();
    private MutableLiveData<ResponsePicture> pic = new MutableLiveData<>();

    public UserRepository() {
        this.service = AlarmMeAPI.getInstance(true).getService();
    }

    public MutableLiveData<ResponseUser> getCurrentUser() {
        Call<ResponseUser> call = service.getCurrentUser();
        call.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                if (response.isSuccessful()) {
                    user.setValue(response.body());
                } else {
                    Gson gson = new Gson();
                    Error error = gson.fromJson(response.errorBody().charStream(), Error.class);
                    Toast.makeText(MyApp.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                Toast.makeText(MyApp.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return user;
    }

    public MutableLiveData<ResponsePicture> getCurrentUserPicture() {
        Call<ResponsePicture> call = service.getCurrentUserPicture();
        call.enqueue(new Callback<ResponsePicture>() {
            @Override
            public void onResponse(Call<ResponsePicture> call, Response<ResponsePicture> response) {
                if (response.isSuccessful()) {
                    pic.setValue(response.body());
                } else {
                    Gson gson = new Gson();
                    Error error = gson.fromJson(response.errorBody().charStream(), Error.class);
                    Toast.makeText(MyApp.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePicture> call, Throwable t) {
                Toast.makeText(MyApp.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return pic;
    }
}