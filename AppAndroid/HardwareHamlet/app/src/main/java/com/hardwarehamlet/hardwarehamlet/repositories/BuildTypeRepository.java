package com.hardwarehamlet.hardwarehamlet.repositories;

import android.content.Context;

import com.hardwarehamlet.hardwarehamlet.data.remote.DataService;
import com.hardwarehamlet.hardwarehamlet.data.remote.DataSource;
import com.hardwarehamlet.hardwarehamlet.data.local.AppDatabase;
import com.hardwarehamlet.hardwarehamlet.model.Build_Type;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuildTypeRepository {
    private static BuildTypeRepository INSTANCE;
    private final DataService dataService;

    private BuildTypeRepository(Context context) {
        this.dataService = DataSource.getDataService();
    }

    public static BuildTypeRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new BuildTypeRepository(context);
        }
        return INSTANCE;
    }

    public static void getBuildTypeList(final Context context, final BuildTypeCallback callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final AppDatabase appDatabase = AppDatabase.getInstance(context);
                final List<Build_Type> build_typeList = appDatabase.getBuild_TypeDAO().getBuildTypeList();
                if(build_typeList.size() > 0){
                    callback.onResult(build_typeList);
                }

                DataService dataService = DataSource.getDataService();

                Call<List<Build_Type>> call = dataService.getBuildTypeList();

                call.enqueue(new Callback<List<Build_Type>>() {
                    @Override
                    public void onResponse(Call<List<Build_Type>> call, Response<List<Build_Type>> response) {
                        if(response.isSuccessful()){
                            final List<Build_Type> buildTypeList = response.body();

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    assert buildTypeList != null;
                                    if(build_typeList.size() != buildTypeList.size()) {
                                        appDatabase.getBuild_TypeDAO().deleteBuildTypeList();
                                        appDatabase.getBuild_TypeDAO().insertBuildType(buildTypeList);
                                        callback.onResult(buildTypeList);
                                    }else if(build_typeList.size() == 0){
                                        appDatabase.getBuild_TypeDAO().insertBuildType(buildTypeList);
                                        callback.onResult(buildTypeList);
                                    }
                                }
                            }).start();
                        }else{
                            callback.onError(response.errorBody().toString());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Build_Type>> call, Throwable t) {

                    }
                });

            }
        }).start();


    }

    public static interface BuildTypeCallback{
        void onResult(List<Build_Type> buildTypeList);
        void onError(String error);
    }

}
