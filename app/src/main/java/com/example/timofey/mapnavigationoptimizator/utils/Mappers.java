package com.example.timofey.mapnavigationoptimizator.utils;

import com.example.timofey.mapnavigationoptimizator.Place;
import com.example.timofey.mapnavigationoptimizator.database.Point;
import com.example.timofey.mapnavigationoptimizator.database.PointEntity;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Timofey on 25.01.2018.
 */

public class Mappers {

    public static Place mapPlace(com.google.android.gms.location.places.Place place) {
        return new Place(place.getId(),
                place.getAddress().toString(),
                place.getLatLng(),
                place.getName().toString());
    }

    public static PointEntity toEntity(Point point) {
        PointEntity pointEntity = new PointEntity();
        pointEntity.setId(point.getId());
        pointEntity.setAddress(point.getAddress());
        pointEntity.setLatitude(point.getLatitude());
        pointEntity.setLongitude(point.getLongitude());
        pointEntity.setName(point.getName());
        return pointEntity;
    }

    public static PointEntity toEntity(Place place) {
        PointEntity pointEntity = new PointEntity();
        pointEntity.setId(place.getId());
        pointEntity.setAddress(place.getAddress());
        LatLng latLng = place.getLatLng();
        pointEntity.setLatitude(latLng.latitude);
        pointEntity.setLongitude(latLng.longitude);
        pointEntity.setName(place.getName());
        return pointEntity;
    }
}
