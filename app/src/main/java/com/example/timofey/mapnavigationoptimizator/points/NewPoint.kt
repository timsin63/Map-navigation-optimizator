package com.example.timofey.mapnavigationoptimizator.points

import com.example.timofey.mapnavigationoptimizator.BasePresenter
import com.example.timofey.mapnavigationoptimizator.Place
import com.example.timofey.mapnavigationoptimizator.database.Point
import io.reactivex.Completable

/**
 * Created by Timofey on 19.01.2018.
 */
class NewPoint {
    interface View{
        fun close()

        fun showEmpty()

        fun setPlaceName(name : String)
    }

    interface Presenter : BasePresenter<View> {
        fun onPlaceSelected(place : Place)

        fun onAddPointClick()
    }

    interface Model {
        fun savePoint(place : Place) : Completable
    }
}