package com.example.timofey.mapnavigationoptimizator.core

/**
 * Created by Timofey on 15.03.2018.
 */
class RoutingMatrix(val timeArray: Array<IntArray>, distanceArray: Array<IntArray>) {

    val size = timeArray[0].size - 1
    val visited = IntArray(size + 1)


}