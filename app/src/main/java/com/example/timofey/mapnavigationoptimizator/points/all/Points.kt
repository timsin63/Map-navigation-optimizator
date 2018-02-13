package com.example.timofey.mapnavigationoptimizator.points.all

import com.example.timofey.mapnavigationoptimizator.BasePresenter
import com.example.timofey.mapnavigationoptimizator.database.Point
import com.example.timofey.mapnavigationoptimizator.points.newpoint.NewPoint
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Created by Timofey on 12.02.2018.
 */
class Points {
    interface Model {
        fun getPointList() : Single<List<Point>>
    }

    interface Presenter : BasePresenter<Points.View>{
        fun onButtonClicked()
    }

    interface View  {
        fun setPoints(list: List<Point>)
    }
}