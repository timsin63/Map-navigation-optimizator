package com.example.timofey.mapnavigationoptimizator;

import android.content.Context;

import com.example.timofey.mapnavigationoptimizator.database.Models;
import com.example.timofey.mapnavigationoptimizator.database.PointEntity;
import com.example.timofey.mapnavigationoptimizator.database.PointRepository;

import io.requery.android.sqlite.DatabaseSource;
import io.requery.reactivex.ReactiveEntityStore;
import io.requery.reactivex.ReactiveSupport;
import io.requery.sql.Configuration;
import io.requery.sql.EntityDataStore;
import io.requery.sql.TableCreationMode;

/**
 * Created by Timofey on 25.01.2018.
 */

public class DatabaseComponent {

    private PointRepository pointRepository;

    public DatabaseComponent(Context context) {
        DatabaseSource databaseSource = new DatabaseSource(context, Models.DEFAULT, 1);
        if (BuildConfig.DEBUG) {
            databaseSource.setTableCreationMode(TableCreationMode.DROP_CREATE);
        }
        Configuration configuration = databaseSource.getConfiguration();
        ReactiveEntityStore<PointEntity> pointStore = ReactiveSupport.toReactiveStore(new EntityDataStore<PointEntity>(configuration));
        pointRepository = new PointRepository(pointStore);
    }

    public PointRepository getPointRepository() {
        return pointRepository;
    }
}
