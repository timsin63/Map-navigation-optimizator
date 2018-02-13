package com.example.timofey.mapnavigationoptimizator.points.all;

import com.example.timofey.mapnavigationoptimizator.database.Point;
import com.example.timofey.mapnavigationoptimizator.database.PointRepository;

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

    public PointsModel(PointRepository repository) {
        this.repository = repository;
    }

    @NotNull
    @Override
    public Single<List<Point>> getPointList() {
        return repository.getAllPoints().flatMapSingle(entities -> {
            return Single.just(new ArrayList<>(entities));
        });
    }
}
