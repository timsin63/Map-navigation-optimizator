package com.example.timofey.mapnavigationoptimizator.core

import com.example.timofey.mapnavigationoptimizator.database.Point

/**
 * Created by Timofey on 18.03.2018.
 */
class RoutingCalculator {

    fun calculate(routingMatrix: RoutingMatrix) : IntArray {

        val algorithm = GreedyAlgorithm(routingMatrix, RoutingAlgorithm.CalculationType.FAST)

        val list = algorithm.execute()

        return list
    }
}