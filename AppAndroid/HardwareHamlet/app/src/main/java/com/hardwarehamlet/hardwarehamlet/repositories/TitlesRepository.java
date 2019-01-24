package com.hardwarehamlet.hardwarehamlet.repositories;

import android.content.Context;

import com.hardwarehamlet.hardwarehamlet.data.local.AppDatabase;
import com.hardwarehamlet.hardwarehamlet.data.remote.DataService;
import com.hardwarehamlet.hardwarehamlet.data.remote.DataSource;
import com.hardwarehamlet.hardwarehamlet.model.Medals;
import com.hardwarehamlet.hardwarehamlet.model.Titles;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TitlesRepository {
    private static TitlesRepository INSTANCE;
    private final DataService dataService;

    private TitlesRepository(Context context) {
        this.dataService = DataSource.getDataService();
    }



    public static TitlesRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TitlesRepository(context);
        }
        return INSTANCE;
    }

    public static void getTitlesInfo(final Context context){
        final AppDatabase appDatabase = AppDatabase.getInstance(context);
        final DataService dataService = DataSource.getDataService();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Titles> medalsList = appDatabase.getTitlesDAO().getTitlesList();

                Call<List<Titles>> call = dataService.getTitles();
                call.enqueue(new Callback<List<Titles>>() {
                    @Override
                    public void onResponse(Call<List<Titles>> call, Response<List<Titles>> response) {
                        if(response.isSuccessful()){
                            final List<Titles> newTitlesList = response.body();
                            if(newTitlesList.size() != medalsList.size()){
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        appDatabase.getTitlesDAO().deleteTitlesList();
                                        appDatabase.getTitlesDAO().insertTitles(newTitlesList);
                                    }
                                }).start();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Titles>> call, Throwable t) {

                    }
                });
            }
        }).start();
    }
}
