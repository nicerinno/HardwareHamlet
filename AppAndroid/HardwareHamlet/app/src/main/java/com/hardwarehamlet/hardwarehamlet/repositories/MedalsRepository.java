package com.hardwarehamlet.hardwarehamlet.repositories;

import android.content.Context;

import com.hardwarehamlet.hardwarehamlet.data.local.AppDatabase;
import com.hardwarehamlet.hardwarehamlet.data.remote.DataService;
import com.hardwarehamlet.hardwarehamlet.data.remote.DataSource;
import com.hardwarehamlet.hardwarehamlet.model.Medals;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedalsRepository {
    private static MedalsRepository INSTANCE;
    private final DataService dataService;

    private MedalsRepository(Context context) {
        this.dataService = DataSource.getDataService();
    }



    public static MedalsRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new MedalsRepository(context);
        }
        return INSTANCE;
    }

    public static void getMedalsInfo(final Context context){
        final AppDatabase appDatabase = AppDatabase.getInstance(context);
        final DataService dataService = DataSource.getDataService();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Medals> medalsList = appDatabase.getMedalsDAO().getMedalsList();

                Call<List<Medals>> call = dataService.getMedals();
                call.enqueue(new Callback<List<Medals>>() {
                    @Override
                    public void onResponse(Call<List<Medals>> call, Response<List<Medals>> response) {
                        if(response.isSuccessful()){
                            final List<Medals> newMedalsList = response.body();
                            if(newMedalsList.size() != medalsList.size()){
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        appDatabase.getMedalsDAO().deleteMedalsList();
                                        appDatabase.getMedalsDAO().insertMedals(newMedalsList);
                                    }
                                }).start();

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Medals>> call, Throwable t) {

                    }
                });
            }
        }).start();

    }
}
