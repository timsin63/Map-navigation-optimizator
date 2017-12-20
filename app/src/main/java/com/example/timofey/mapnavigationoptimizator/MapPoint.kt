package com.example.timofey.mapnavigationoptimizator

import com.google.android.gms.maps.model.LatLng

/**
 * Created by Timofey on 11.11.2017.
 */

data class MapPoint(val id : String, val name : String, val latLng : LatLng, val address : String)