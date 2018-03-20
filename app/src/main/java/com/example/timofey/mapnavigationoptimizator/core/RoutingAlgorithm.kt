package com.example.timofey.mapnavigationoptimizator.core

import com.example.timofey.mapnavigationoptimizator.database.Point

/**
 * Created by Timofey on 15.03.2018.
 */
abstract class  RoutingAlgorithm(val routingMatrix: RoutingMatrix, val type : CalculationType) {

    var countedTime : Int = 0
    var countedDistance : Int = 0

    abstract fun execute() : IntArray

    enum class CalculationType {
        FAST,
        SHORT
    }
}