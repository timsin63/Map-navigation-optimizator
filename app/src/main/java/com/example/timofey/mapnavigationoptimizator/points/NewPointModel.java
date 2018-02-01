package com.example.timofey.mapnavigationoptimizator.points;

import android.support.annotation.NonNull;

import com.example.timofey.mapnavigationoptimizator.Place;
import com.example.timofey.mapnavigationoptimizator.database.Point;
import com.example.timofey.mapnavigationoptimizator.database.PointRepository;
import com.example.timofey.mapnavigationoptimizator.utils.Mappers;

/**
 * Created by Timofey on 22.01.2018.
 */

public class NewPointModel implements NewPoint.Model {

    private PointRepository pointRepository;

    public NewPointModel(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    @Override
    public void savePoint(@NonNull Place place) {
        pointRepository.savePoint(Mappers.toEntity(place));
    }
}
