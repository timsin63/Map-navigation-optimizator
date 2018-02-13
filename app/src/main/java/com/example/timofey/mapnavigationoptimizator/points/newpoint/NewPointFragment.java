package com.example.timofey.mapnavigationoptimizator.points.newpoint;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timofey.mapnavigationoptimizator.R;
import com.example.timofey.mapnavigationoptimizator.utils.Mappers;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Timofey on 19.01.2018.
 */

public class NewPointFragment extends Fragment implements NewPoint.View {

    public static final String TAG = "NEW_POINT_FRAGMENT";

    private NewPoint.Presenter presenter;

    private TextView pointName;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.f_new_point, container, false);
        pointName = (TextView) view.findViewById(R.id.chosen_point);

        Button addPointButton = (Button) view.findViewById(R.id.btn_new_point);
        addPointButton.setOnClickListener(v -> presenter.onAddPointClick());

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportPlaceAutocompleteFragment autocompleteFragment = (SupportPlaceAutocompleteFragment) getChildFragmentManager()
                .findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setHint(getString(R.string.place_autocomplete_hint));

        if (autocompleteFragment == null) {
            autocompleteFragment = (SupportPlaceAutocompleteFragment) SupportPlaceAutocompleteFragment
                    .instantiate(getContext(),
                            getString(R.string.place_autocomplete_class_name));
        }


        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Log.i(TAG, "Place: " + place.getName());
                presenter.onPlaceSelected(Mappers.mapPlace(place));
            }

            @Override
            public void onError(Status status) {
                Log.i(TAG, "An error occurred: " + status);
            }
        });
    }

    public void setPresenter(NewPoint.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void close() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void showEmpty() {
        Toast.makeText(getContext(), R.string.place_not_chosen, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPlaceName(@NotNull String name) {
        pointName.setText(getString(R.string.chosen_place) + name);
    }
}
