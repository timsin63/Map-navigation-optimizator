package com.example.timofey.mapnavigationoptimizator.points;

import android.support.annotation.NonNull;

import com.example.timofey.mapnavigationoptimizator.Place;
import com.example.timofey.mapnavigationoptimizator.database.Point;
import com.example.timofey.mapnavigationoptimizator.database.PointRepository;
import com.example.timofey.mapnavigationoptimizator.utils.Mappers;

import io.reactivex.Completable;
import io.reactivex.internal.operators.completable.CompletableObserveOn;

/**
 * Created by Timofey on 22.01.2018.
 */

public class NewPointModel implements NewPoint.Model {

    private PointRepository pointRepository;

    public NewPointModel(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    @Override
    public Completable savePoint(@NonNull Place place) {
        return pointRepository.savePoint(Mappers.toEntity(place));
    }
}
