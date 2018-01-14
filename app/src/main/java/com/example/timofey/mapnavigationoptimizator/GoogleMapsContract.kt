package com.example.timofey.mapnavigationoptimizator


import com.example.timofey.mapnavigationoptimizator.remote.PlacesRsp
import com.google.android.gms.location.places.Place
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Timofey on 25.12.2017.
 */
class GoogleMapsContract {
    interface View {

    }

    interface Presenter : BasePresenter<View> {
        fun onPlaceSelected(place : Place)
    }

    interface Model {
        fun getDimensionMatrix() : Single<PlacesRsp>
    }
}