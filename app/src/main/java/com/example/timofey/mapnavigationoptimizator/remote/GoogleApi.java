package com.example.timofey.mapnavigationoptimizator.remote;

import com.google.android.gms.location.places.Place;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Timofey on 17.12.2017.
 */

public interface GoogleApi {

    @GET()
    Call<PlacesRsp> getDimensions(@Url String url);
}