package com.example.timofey.mapnavigationoptimizator.remote

/**
 * Created by Timofey on 20.12.2017.
 */
class PlacesRsp(val destination_addresses : ArrayList<String>,
                val origin_addresses : ArrayList<String>,
                val rows : ArrayList<DimensionRowsRsp>)