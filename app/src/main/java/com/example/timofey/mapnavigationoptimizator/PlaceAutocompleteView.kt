package com.example.timofey.mapnavigationoptimizator

import com.google.android.gms.common.api.Status
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment
import com.google.android.gms.location.places.ui.PlaceSelectionListener

/**
 * Created by Timofey on 17.11.2017.
 */
class PlaceAutocomplete : PlaceAutocompleteFragment {

    val placeSelectedListener : PlaceSelectedListener? = null

    constructor() {
        this.setOnPlaceSelectedListener(placeSelectedListener)
    }

    class PlaceSelectedListener : PlaceSelectionListener {

        val places : ArrayList<Place> = arrayListOf()
        val apiModel : GoogleApiModel = GoogleApiModel()

        override fun onPlaceSelected(place: Place?) {
            places.add(place!!)     //REMOVE! Test data
            apiModel.dimensionMatrix
        }

        override fun onError(p0: Status?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}