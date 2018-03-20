package com.example.timofey.mapnavigationoptimizator.points.all;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.timofey.mapnavigationoptimizator.App;
import com.example.timofey.mapnavigationoptimizator.GoogleApiModel;
import com.example.timofey.mapnavigationoptimizator.R;
import com.example.timofey.mapnavigationoptimizator.database.Point;
import com.example.timofey.mapnavigationoptimizator.map.GoogleMapModel;
import com.example.timofey.mapnavigationoptimizator.map.GoogleMapsFragment;
import com.example.timofey.mapnavigationoptimizator.map.GoogleMapsPresenter;
import com.google.android.gms.maps.MapFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Timofey on 10.02.2018.
 */

public class PointsFragment extends Fragment implements Points.View {

    public static final String TAG = "POINTS_FRAGMENT";

    private RecyclerView pointListView;
    private Button startRoutingButton;
    private Points.Presenter presenter;

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
        View view = inflater.inflate(R.layout.f_points, container, false);

        pointListView = (RecyclerView) view.findViewById(R.id.point_list);
        pointListView.setLayoutManager(new LinearLayoutManager(getContext()));

        startRoutingButton = (Button) view.findViewById(R.id.btn_start_routing);

        startRoutingButton.setOnClickListener(v -> {
            presenter.onButtonClicked();
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void setPresenter(Points.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setPoints(@NotNull List<? extends Point> list) {
        PointsAdapter adapter = new PointsAdapter(list);
        pointListView.setAdapter(adapter);
    }

    @Override
    public void openMapWithResult(@NotNull int[] intArray) {

//        GoogleMapsFragment fragment = (GoogleMapsFragment) getFragmentManager().findFragmentByTag(GoogleMapsFragment.TAG);
        GoogleMapsFragment fragment = new GoogleMapsFragment();
        fragment.setPresenter(
                new GoogleMapsPresenter(
                        new GoogleMapModel(
                                App.getDatabaseComponent()
                                        .getPointRepository(), new GoogleApiModel())));
        Bundle bundle = new Bundle();
        bundle.putSerializable("point_array", intArray);

        if (fragment.getArguments() == null || !fragment.isRemoving()) {
            fragment.setArguments(bundle);
        } else {
            fragment.getArguments().putAll(bundle);
        }


        getFragmentManager().beginTransaction()
                .replace(R.id.activity_main_root, fragment)
                .addToBackStack(null)
                .commit();
    }
}
