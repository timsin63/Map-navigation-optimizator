package com.example.timofey.mapnavigationoptimizator.database;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.requery.reactivex.ReactiveEntityStore;

/**
 * Created by Timofey on 22.01.2018.
 */

public class PointRepository {

    private final ReactiveEntityStore<PointEntity> store;

    public PointRepository(ReactiveEntityStore store) {
        this.store = store;
    }

    public Completable savePoint(PointEntity entity) {
        return store
                .insert(entity)
                .toCompletable();
    }

    public Maybe<List<PointEntity>> getAllPoints() {
        return Maybe.just(store
                .select(PointEntity.class)
                .get()
                .toList());
    }
}
