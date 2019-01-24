package com.hardwarehamlet.hardwarehamlet.repositories;

import android.content.Context;

import com.hardwarehamlet.hardwarehamlet.data.remote.DataService;
import com.hardwarehamlet.hardwarehamlet.data.remote.DataSource;
import com.hardwarehamlet.hardwarehamlet.model.ResponseBody;
import com.hardwarehamlet.hardwarehamlet.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRepository {
    private static UsersRepository INSTANCE;
    private final DataService dataService;

    private UsersRepository(Context context) {
        this.dataService = DataSource.getDataService();
    }



    public static UsersRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new UsersRepository(context);
        }
        return INSTANCE;
    }

    public static void getUserById(Context context, final UsersRepository.UsersCallback callback, long user_id){
        DataService dataService = DataSource.getDataService();

        Call<Users> call = dataService.getUserById(user_id);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if(response.isSuccessful()){
                    Users user = response.body();
                    callback.onResult(user);
                }else{
                    callback.onError(response.errorBody().toString());
                }
            }
            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });
    }

    public static void getUserByUsername(Context context, final UserIdCallback callback, String username){
        DataService dataService = DataSource.getDataService();

        Call<ResponseBody> call = dataService.getUserByUsername(username);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    ResponseBody responseBody = response.body();
                    callback.onResult(Long.parseLong(responseBody.getResult()));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public static interface UsersCallback{
        void onResult(Users userList);
        void onError(String error);
    }

    public static interface UserIdCallback{
        void onResult(long id);
    }
}
