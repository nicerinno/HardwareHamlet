package com.hardwarehamlet.hardwarehamlet.repositories;

import android.content.Context;

import com.hardwarehamlet.hardwarehamlet.data.remote.DataService;
import com.hardwarehamlet.hardwarehamlet.data.remote.DataSource;
import com.hardwarehamlet.hardwarehamlet.model.Builds;
import com.hardwarehamlet.hardwarehamlet.model.BuildsResponseBody;
import com.hardwarehamlet.hardwarehamlet.model.Components;
import com.hardwarehamlet.hardwarehamlet.model.Users;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankingRepository {

    private static RankingRepository INSTANCE;
    private final DataService dataService;

    private RankingRepository(Context context){
        this.dataService = DataSource.getDataService();
    }

    public static RankingRepository getINSTANCE(Context context){
        if(INSTANCE == null){
           INSTANCE = new RankingRepository(context);
        }
    return INSTANCE;
    }

    public static void getRankingUsers(Context context, final RankingsCallback topUserCall){
        DataService dataService = DataSource.getDataService();
        Call<List<Users>> call = dataService.getRankingUsers();

        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if(response.isSuccessful()){
                    List<Users> usersList = response.body();
                    topUserCall.onResult(usersList);
                }
                else
                {
                    topUserCall.onError(response.errorBody().toString());
                }
            }
            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

            }
        });
    }

    public static void getRankingComponents(Context context, final RankingsCallback topComponentsCall){
        DataService dataService = DataSource.getDataService();
        Call<List<Components>> call = dataService.getRankingComponents();

        call.enqueue(new Callback<List<Components>>() {
            @Override
            public void onResponse(Call<List<Components>> call, Response<List<Components>> response) {
                if(response.isSuccessful()){
                    List<Components> componentsList = response.body();
                    topComponentsCall.onResult(componentsList);

                }else{
                    topComponentsCall.onError(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Components>> call, Throwable t) {

            }
        });
    }

    public static void getRankingBuilds(Context context, final RankingsCallback topBuildsCall){
        DataService dataService = DataSource.getDataService();
        Call<List<BuildsResponseBody>> call = dataService.getRankingBuilds();

        call.enqueue(new Callback<List<BuildsResponseBody>>() {
            @Override
            public void onResponse(Call<List<BuildsResponseBody>> call, Response<List<BuildsResponseBody>> response) {
                if(response.isSuccessful()){
                    List<BuildsResponseBody> buildsResponseBody = response.body();
                    List<Builds> buildsList = new ArrayList<>();
                    for (int i = 0; i < buildsResponseBody.size(); i++) {
                        buildsList.add(buildsResponseBody.get(i).getBuild());
                    }
                    topBuildsCall.onResult(buildsList);
                }else {
                    topBuildsCall.onError(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<BuildsResponseBody>> call, Throwable t) {

            }
        });

    }

    public static interface RankingsCallback<T>{
        void onResult(List<T> list);
        void onError(String error);
    }
}
