package com.hardwarehamlet.hardwarehamlet.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataSource {
    //Em casa
    private static final String BASE_URL = "http://10.0.2.2/pi3/";


    //Na escola
    //private static final String BASE_URL = "http://194.210.139.1/api/";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static DataService dataService;

    public static DataService getDataService() {
        if (dataService == null) {
            dataService = retrofit.create(DataService.class);
        }
        return dataService;
    }

}
