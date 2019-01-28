package com.hardwarehamlet.hardwarehamlet.repositories;

import android.content.Context;

import com.hardwarehamlet.hardwarehamlet.data.remote.DataService;
import com.hardwarehamlet.hardwarehamlet.data.remote.DataSource;
import com.hardwarehamlet.hardwarehamlet.data.local.AppDatabase;
import com.hardwarehamlet.hardwarehamlet.model.Components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComponentsRepository {
    private static ComponentsRepository INSTANCE;
    private final DataService dataService;

    private ComponentsRepository(Context context) {
        this.dataService = DataSource.getDataService();
    }



    public static ComponentsRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ComponentsRepository(context);
        }
        return INSTANCE;
    }

//    public static void getComponentFromBuild(final Context context){
//        getComponents();
//    }

    public static void getComponentById(final Context context, final ComponentsListCallback callback, final long component_id){
        final AppDatabase appDatabase = AppDatabase.getInstance(context);
        new Thread(new Runnable() {
            @Override
            public void run() {
                //List<Components> list = appDatabase.getComponentsDAO().getComponentsList();
                final Components component = appDatabase.getComponentsDAO().getComponentById(component_id);
                callback.onResult(component);

                DataService dataService = DataSource.getDataService();

                Call<Components> call = dataService.getComponentById(component_id);
                call.enqueue(new Callback<Components>() {
                    @Override
                    public void onResponse(Call<Components> call, final Response<Components> response) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                if(response.isSuccessful()){
                                    Components newComponent = response.body();
                                    callback.onResult(newComponent);
                                }else{
                                    callback.onError(""+response.code());
                                }
                            }
                        }).start();
                    }

                    @Override
                    public void onFailure(Call<Components> call, Throwable t) {

                    }
                });


            }
        }).start();
    }

    public static void getComponents(final Context context, final ComponentsListCallback callback, final String sort, final int type_id, final String search){
        final DataService dataService = DataSource.getDataService();
        final AppDatabase appDatabase = AppDatabase.getInstance(context);
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Components> componentsList = new ArrayList<>();
                if(search.equals("none")){
                    if(sort.equals("asc")){
                        componentsList = appDatabase.getComponentsDAO().getComponentsWithoutSearchASC(type_id);
                    }else{
                        componentsList = appDatabase.getComponentsDAO().getComponentsWithoutSearchDESC(type_id);
                    }
                }else{
                    String name = "";
                    String brand = "";
                    if(search.contains(" ")){
                        brand = "%" + search.substring(0,search.indexOf(" ")) + "%";
                        name = "%" + search.substring(search.indexOf(" "),search.length()) + "%";
                    }else{
                        brand = name = "%" + search + "%";
                    }

                    if(sort.equals("asc")){
                        componentsList = appDatabase.getComponentsDAO().getAllComponentsWithTypeASC(name,brand, type_id);
                    }else{

                        componentsList = appDatabase.getComponentsDAO().getAllComponentsWithTypeDESC(name,brand, type_id);

                    }
                }
                callback.onResult(componentsList);

                final List<Components> dbComponentsList = componentsList;
                Map<String,String> data = new HashMap<>();
                data.put("price_order",sort);
                data.put("component_type_id",String.valueOf(type_id));
                data.put("search",search);

                Call<List<Components>> call = dataService.getAllComponents(data);
                call.enqueue(new Callback<List<Components>>() {
                    @Override
                    public void onResponse(Call<List<Components>> call, final Response<List<Components>> response) {
                        if(response.isSuccessful()){
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    List<Components> newComponentsList = response.body();
                                    if(dbComponentsList.size() != newComponentsList.size()) {
                                        appDatabase.getComponentsDAO().deleteAllComponents();
                                        appDatabase.getComponentsDAO().insertComponents(newComponentsList);
                                        callback.onResult(newComponentsList);
                                    }else{
                                        callback.onResult(dbComponentsList);
                                    }
                                }
                            }).start();

                        }else{
                            callback.onError("Couldn't fecth. Erro: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<com.hardwarehamlet.hardwarehamlet.model.Components>> call, Throwable t) {
                        callback.onError("Couldn't fetch. Failure");
                        t.printStackTrace();
                    }
                });
            }
        }).start();
    }

    public static interface ComponentsListCallback {
        void onResult(List<Components> componentsList);
        void onResult(Components component);
        void onError(String error);
    }

    public static interface ComponentFromBuildCallback{

    }
}
