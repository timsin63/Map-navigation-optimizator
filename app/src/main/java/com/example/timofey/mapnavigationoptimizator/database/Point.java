package com.example.timofey.mapnavigationoptimizator.database;

import io.requery.Entity;

/**
 * Created by Timofey on 22.01.2018.
 */

@Entity
public interface Point {

    String getId();

    String getName();

    String getAddress();

    double getLatitude();

    double getLongitude();

    int getNumber();
}
