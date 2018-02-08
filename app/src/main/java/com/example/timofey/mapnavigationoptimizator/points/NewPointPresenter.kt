package com.example.timofey.mapnavigationoptimizator.points

import com.example.timofey.mapnavigationoptimizator.Place
import com.example.timofey.mapnavigationoptimizator.database.Point
import io.reactivex.functions.Action

/**
 * Created by Timofey on 20.01.2018.
 */
class NewPointPresenter(newPointModel: NewPointModel) : NewPoint.Presenter {
    lateinit var view: NewPoint.View
    lateinit var chosenPlace : Place
    private val model = newPointModel;

    override fun bind(view: NewPoint.View) {
        this.view = view
    }

    override fun unbind(view: NewPoint.View) {
    }


    override fun onPlaceSelected(place: Place) {
        this.chosenPlace = place
        view.setPlaceName(place.name)
    }

    override fun onAddPointClick() {
        model.savePoint(chosenPlace)
                .subscribe(Action { view.close() })
    }
}