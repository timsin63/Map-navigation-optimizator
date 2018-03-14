package com.example.timofey.mapnavigationoptimizator;

import android.app.Application;

import com.example.timofey.mapnavigationoptimizator.remote.GoogleApi;
import com.facebook.stetho.Stetho;

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
                .baseUrl("https://maps.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        googleApi = retrofit.create(GoogleApi.class);

        databaseComponent = new DatabaseComponent(this);

        Stetho.InitializerBuilder initializerBuilder = Stetho.newInitializerBuilder(this);
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this));
        initializerBuilder.enableDumpapp(Stetho.defaultDumperPluginsProvider(this));
        Stetho.Initializer initializer = initializerBuilder.build();
        Stetho.initialize(initializer);
    }

    public static GoogleApi getGoogleApi() {
        return googleApi;
    }

    public static DatabaseComponent getDatabaseComponent() {
        return databaseComponent;
    }
}
