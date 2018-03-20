package com.example.timofey.mapnavigationoptimizator.map;

import android.util.Log;

import com.example.timofey.mapnavigationoptimizator.GoogleApiModel;
import com.example.timofey.mapnavigationoptimizator.database.Point;
import com.google.android.gms.location.places.Place;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Timofey on 25.12.2017.
 */

public class GoogleMapsPresenter implements GoogleMaps.Presenter {

    private GoogleMaps.View view;
    private GoogleMaps.Model model;
    private CompositeDisposable disposables;
    List<Point> points;
    int arr[];

    public GoogleMapsPresenter(GoogleMaps.Model model) {
        this.model = model;
    }

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

    }

    @Override
    public void onNewPointClicked() {
        view.openNewPointView();
    }

    @Override
    public void onPointListClicked() {
        view.openPointListView();
    }

    @Override
    public void onMapReady() {
        model.getSavedPlaces().subscribe(points -> {
            if (arr != null) {
                view.showRoute(arr, points);
            } else {
                view.setMarkers(points);
            }
        });
    }

    public void onRouteReady(int[] arr) {
        this.arr = arr;
        model.getSavedPlaces().subscribe(points -> {
            try {
                view.showRoute(arr, points);
            } catch (Exception e) {
                view.setUpMap();
            }
        });
    }
}
