package com.example.timofey.mapnavigationoptimizator.map;


import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.timofey.mapnavigationoptimizator.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ru.ngs.floatingactionbutton.FloatingActionButton;

import static android.content.Context.LOCATION_SERVICE;

public class GoogleMapsFragment extends Fragment implements GoogleMapsContract.View, OnMapReadyCallback {

    public final static String TAG = "Google_Maps_Fragment";
    private GoogleMap googleMap;
    private View view = null;
    private SupportMapFragment supportMapFragment;
    private SupportPlaceAutocompleteFragment placeAutocompleteFragment;

    private GoogleMapsContract.Presenter presenter;

    public GoogleMapsFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new GoogleMapsPresenter();
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
            view = inflater.inflate(com.example.timofey.mapnavigationoptimizator.R.layout.f_google_maps, container, false);
        }

        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        placeAutocompleteFragment = (SupportPlaceAutocompleteFragment) getActivity()
                .getSupportFragmentManager()
                .findFragmentById(R.id.place_autocomplete_fragment);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        placeAutocompleteFragment = (SupportPlaceAutocompleteFragment) getChildFragmentManager()
                .findFragmentById(R.id.place_autocomplete_fragment);

        placeAutocompleteFragment.getView().setVisibility(View.GONE);

        placeAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                presenter.onPlaceSelected(place);
            }

            @Override
            public void onError(Status status) {

            }
        });

        FloatingActionButton buttonAdd = (FloatingActionButton) getActivity().findViewById(R.id.btn_add);
        buttonAdd.setOnClickListener(v -> {
            placeAutocompleteFragment.getView().setVisibility(View.VISIBLE);
            placeAutocompleteFragment.getView().requestFocus();
        });
    }



    private void initPlaceCompleteView() {
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment) getActivity()
                .getFragmentManager()
                .findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());

                String placeDetailsStr = place.getName() + "\n"
                        + place.getId() + "\n"
                        + place.getLatLng().toString() + "\n"
                        + place.getAddress() + "\n"
                        + place.getAttributions();

                Toast.makeText(getContext().getApplicationContext(), placeDetailsStr, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (googleMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMapAsync(gMap -> {
                        googleMap = gMap;
                        setUpMap();
                    });
            // Check if we were successful in obtaining the map.
        }
    }

    @SuppressLint("MissingPermission")
    private void setUpMap() {

        googleMap.setMyLocationEnabled(true);

        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(true);

        //TODO Using different location providers to get location
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        googleMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())));

        googleMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        setUpMap();
    }


}
