package com.example.timofey.mapnavigationoptimizator;

import android.app.Application;

import com.example.timofey.mapnavigationoptimizator.remote.GoogleApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Timofey on 25.12.2017.
 */

public class App extends Application {

    private Retrofit retrofit;
    private static GoogleApi googleApi;
    private static DatabaseComponent databaseComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        googleApi = retrofit.create(GoogleApi.class);

        databaseComponent = new DatabaseComponent(this);
    }

    public static GoogleApi getGoogleApi() {
        return googleApi;
    }

    public static DatabaseComponent getDatabaseComponent() {
        return databaseComponent;
    }
}
