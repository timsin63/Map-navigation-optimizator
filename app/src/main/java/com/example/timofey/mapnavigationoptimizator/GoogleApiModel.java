package com.example.timofey.mapnavigationoptimizator;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.timofey.mapnavigationoptimizator.App;
import com.example.timofey.mapnavigationoptimizator.database.Point;
import com.example.timofey.mapnavigationoptimizator.map.GoogleMaps;
import com.example.timofey.mapnavigationoptimizator.remote.PlacesRsp;
import com.google.android.gms.location.places.Place;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Timofey on 19.10.2017.
 */

public class GoogleApiModel {

    public static final String TAG = "GOOGLE_API_MODEL";

    @NonNull
    public Single<PlacesRsp> getDimensionMatrix(List<Point> points) {
        try {
            return Single.create(emitter -> {
                String placeNames = "";
                for (Point point : points) {
                    placeNames += point.getName() + "|";
                }
                placeNames = placeNames.substring(0, placeNames.length()-1);

                Call<PlacesRsp> call = App.getGoogleApi()
                        .getDimensions(placeNames,
                                placeNames,
                                "drive",
                                "ru",
//                                "AIzaSyDuHCULsqYN16XlLESVfl4wjG6CWzEV_UU");
                                "AIzaSyB3MIl-PU9hV4rvq0uheFh7AvOJa4RWkPI");
                Response<PlacesRsp> response = call.execute();
                PlacesRsp rsp = response.body();
                emitter.onSuccess(rsp);
            });
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
            return null;
        }
    }
}
