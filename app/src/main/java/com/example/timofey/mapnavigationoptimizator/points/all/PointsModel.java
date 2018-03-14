package com.example.timofey.mapnavigationoptimizator.points.all;

import com.example.timofey.mapnavigationoptimizator.GoogleApiModel;
import com.example.timofey.mapnavigationoptimizator.database.Point;
import com.example.timofey.mapnavigationoptimizator.database.PointRepository;
import com.example.timofey.mapnavigationoptimizator.remote.PlacesRsp;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by Timofey on 12.02.2018.
 */

public class PointsModel implements Points.Model {

    private PointRepository repository;
    private GoogleApiModel apiModel;

    public PointsModel(PointRepository repository, GoogleApiModel apiModel) {
        this.repository = repository;
        this.apiModel = apiModel;
    }

    @NotNull
    @Override
    public Single<List<Point>> getPointList() {
        return repository.getAllPoints().flatMapSingle(entities -> {
            return Single.just(new ArrayList<>(entities));
        });
    }

    @NotNull
    @Override
    public Single<PlacesRsp> getDimensionsMatrix(@NotNull List<? extends Point> points) {
        return apiModel.getDimensionMatrix((List<Point>) points);
    }
}
