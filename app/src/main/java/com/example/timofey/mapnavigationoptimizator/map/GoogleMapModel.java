package com.example.timofey.mapnavigationoptimizator.map;

import com.example.timofey.mapnavigationoptimizator.GoogleApiModel;
import com.example.timofey.mapnavigationoptimizator.database.Point;
import com.example.timofey.mapnavigationoptimizator.database.PointEntity;
import com.example.timofey.mapnavigationoptimizator.database.PointRepository;
import com.example.timofey.mapnavigationoptimizator.remote.PlacesRsp;
import com.example.timofey.mapnavigationoptimizator.utils.Mappers;
import com.google.android.gms.location.places.Place;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by Timofey on 04.02.2018.
 */

public class GoogleMapModel implements GoogleMaps.Model {

    private final PointRepository repository;
    private final GoogleApiModel apiModel;

    public GoogleMapModel(PointRepository repository, GoogleApiModel apiModel) {
        this.repository = repository;
        this.apiModel = apiModel;
    }

    @NotNull
    @Override
    public Single<PlacesRsp> getDimensionMatrix() {
        return apiModel.getDimensionMatrix();
    }

    @NotNull
    @Override
    public Single<List<Point>> getSavedPlaces() {
        return repository.getAllPoints().flatMapSingle(pointEntities -> {
            return Single.just(new ArrayList<>(pointEntities));
        });
    }
}
