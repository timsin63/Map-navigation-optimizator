package com.example.timofey.mapnavigationoptimizator.map;

import android.util.Log;

import com.example.timofey.mapnavigationoptimizator.GoogleApiModel;
import com.google.android.gms.location.places.Place;

import org.jetbrains.annotations.NotNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Timofey on 25.12.2017.
 */

public class GoogleMapsPresenter implements GoogleMaps.Presenter {

    private GoogleMaps.View view;
    private GoogleMaps.Model model = new GoogleApiModel();
    private CompositeDisposable disposables;

    @Override
    public void bind(GoogleMaps.View view) {
        this.view = view;
        disposables = new CompositeDisposable();
    }

    @Override
    public void unbind(GoogleMaps.View view) {
        disposables.dispose();
        disposables = null;
        this.view = null;
    }

    @Override
    public void onPlaceSelected(@NotNull Place place) {
        disposables.add(model
                .getDimensionMatrix()
                .subscribeOn(Schedulers.newThread())
                .subscribe(s -> {
                    Log.d("Matrix", s.toString());
                }));
    }

    @Override
    public void onNewPointClicked() {
        view.openNewPointView();
    }

    @Override
    public void onPointListClicked() {

    }
}
