package com.example.timofey.mapnavigationoptimizator.map;


import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.timofey.mapnavigationoptimizator.App;
import com.example.timofey.mapnavigationoptimizator.GoogleApiModel;
import com.example.timofey.mapnavigationoptimizator.R;
import com.example.timofey.mapnavigationoptimizator.database.Point;
import com.example.timofey.mapnavigationoptimizator.points.all.PointsFragment;
import com.example.timofey.mapnavigationoptimizator.points.all.PointsModel;
import com.example.timofey.mapnavigationoptimizator.points.all.PointsPresenter;
import com.example.timofey.mapnavigationoptimizator.points.newpoint.NewPointFragment;
import com.example.timofey.mapnavigationoptimizator.points.newpoint.NewPointModel;
import com.example.timofey.mapnavigationoptimizator.points.newpoint.NewPointPresenter;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

public class GoogleMapsFragment extends Fragment implements GoogleMaps.View, OnMapReadyCallback {

    public final static String TAG = "GOOGLE_MAPS_FRAGMENT";
    private static final int CAMERA_BOUNDS_PADDING = 10;

    private GoogleMap googleMap;
    private View view = null;
    private SupportMapFragment supportMapFragment;

    private NewPointFragment newPointFragment;

    private GoogleMaps.Presenter presenter;

    public GoogleMapsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.bind(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.unbind(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (view == null) {
            view = inflater.inflate(R.layout.f_google_maps, container, false);
        }

        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        newPointFragment = (NewPointFragment) getFragmentManager().findFragmentByTag(NewPointFragment.TAG);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonAdd = (Button) getActivity().findViewById(R.id.btn_add);
        buttonAdd.setOnClickListener(v -> {
            presenter.onNewPointClicked();
        });

        Button buttonList = (Button) getActivity().findViewById(R.id.btn_list);
        buttonList.setOnClickListener(v -> {
            presenter.onPointListClicked();
        });

        setUpMap();
    }


    @SuppressLint("MissingPermission")
    public void setUpMap() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (googleMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            supportMapFragment
                    .getMapAsync(this);
        } else {
            onMapReady(googleMap);
        }
    }

    public void setPresenter(GoogleMaps.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void openNewPointView() {
        NewPointFragment newPointFragment = (NewPointFragment) getFragmentManager()
                .findFragmentByTag(NewPointFragment.TAG);

        if (newPointFragment == null) {
            newPointFragment = new NewPointFragment();
            NewPointModel newPointModel = new NewPointModel(App.getDatabaseComponent().getPointRepository());
            NewPointPresenter newPointPresenter = new NewPointPresenter(newPointModel);
            newPointFragment.setPresenter(newPointPresenter);
        }
        getFragmentManager().beginTransaction()
                .replace(R.id.activity_main_root, newPointFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openPointListView() {
        PointsFragment pointsFragment = (PointsFragment) getFragmentManager()
                .findFragmentByTag(PointsFragment.TAG);

        if (pointsFragment == null) {
            pointsFragment = new PointsFragment();
            PointsModel pointsModel = new PointsModel(App.getDatabaseComponent().getPointRepository(), new GoogleApiModel());
            PointsPresenter pointsPresenter = new PointsPresenter(pointsModel);
            pointsFragment.setPresenter(pointsPresenter);
        }

        getFragmentManager().beginTransaction()
                .replace(R.id.activity_main_root, pointsFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setMarkers(@NotNull List<? extends Point> points) {
        if (points.isEmpty()) return;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Point point : points) {
            LatLng position = new LatLng(point.getLatitude(), point.getLongitude());
            googleMap.addMarker(new MarkerOptions()
                    .position(position))
                    .setTitle(point.getName());
            builder.include(position);
        }
        LatLngBounds bounds = builder.build();

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, CAMERA_BOUNDS_PADDING);
        try {
            googleMap.animateCamera(cameraUpdate);
        } catch (Exception ignored) {}
    }

    @Override
    public boolean isMapReady() {
        return googleMap != null;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMyLocationEnabled(true);

        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(true);

        //TODO Using different location providers to get location
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location != null) {

            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(location.getLatitude(), location.getLongitude()))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.current_position_arrow)));
        }
        presenter.onMapReady();
    }
}
