package com.example.timofey.mapnavigationoptimizator.map


import com.example.timofey.mapnavigationoptimizator.BasePresenter
import com.example.timofey.mapnavigationoptimizator.database.Point
import com.example.timofey.mapnavigationoptimizator.remote.PlacesRsp
import com.google.android.gms.location.places.Place
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Timofey on 25.12.2017.
 */
class GoogleMaps {
    interface View {
        fun openNewPointView()

        fun openPointListView()

        fun setMarkers(points : List<Point>)

        fun isMapReady() : Boolean

        fun setUpMap()
    }

    interface Presenter : BasePresenter<View> {
        fun onPlaceSelected(place : Place)

        fun onNewPointClicked()

        fun onPointListClicked()

        fun onMapReady()
    }

    interface Model {
        fun getDimensionMatrix() : Single<PlacesRsp>

        fun getSavedPlaces() : Single<List<Point>>
    }
}