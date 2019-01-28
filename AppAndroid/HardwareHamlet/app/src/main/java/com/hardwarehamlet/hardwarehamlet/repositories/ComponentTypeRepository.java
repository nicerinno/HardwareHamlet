package com.hardwarehamlet.hardwarehamlet.repositories;

import android.content.Context;

import com.hardwarehamlet.hardwarehamlet.data.remote.DataService;
import com.hardwarehamlet.hardwarehamlet.data.remote.DataSource;
import com.hardwarehamlet.hardwarehamlet.data.local.AppDatabase;
import com.hardwarehamlet.hardwarehamlet.model.Component_Type;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComponentTypeRepository {
    private static ComponentTypeRepository INSTANCE;
    private final DataService dataService;

    private ComponentTypeRepository(Context context) {
        this.dataService = DataSource.getDataService();
    }

    public static ComponentTypeRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ComponentTypeRepository(context);
        }
        return INSTANCE;
    }

    public static void getComponentTypeList(final Context context, final ComponentTypeCallback callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final AppDatabase appDatabase = AppDatabase.getInstance(context);
                final List<Component_Type> componentTypeList = appDatabase.getComponentsDAO().getComponentTypeList();
                callback.onResult(componentTypeList);

                DataService dataService = DataSource.getDataService();

                Call<List<Component_Type>> call = dataService.getComponentTypeList();

                call.enqueue(new Callback<List<Component_Type>>() {
                    @Override
                    public void onResponse(Call<List<Component_Type>> call, Response<List<Component_Type>> response) {
                        if(response.isSuccessful()){
                            final List<Component_Type> component_typeList = response.body();
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    if(component_typeList.size() != componentTypeList.size()){
                                        appDatabase.getComponentsDAO().deleteComponentTypeList();
                                        appDatabase.getComponentsDAO().insertComponentTypeList(component_typeList);
                                        callback.onResult(component_typeList);
                                    }else if(componentTypeList.size() == 0){
                                        appDatabase.getComponentsDAO().deleteComponentTypeList();
                                        appDatabase.getComponentsDAO().insertComponentTypeList(component_typeList);
                                        callback.onResult(component_typeList);
                                    }
                                }
                            }).start();

                        }else{
                            callback.onError(response.errorBody().toString());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Component_Type>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

            }
        }).start();
    }

    public static interface ComponentTypeCallback {
        void onResult(List<Component_Type> component_typeList);
        void onError(String error);
    }
}
