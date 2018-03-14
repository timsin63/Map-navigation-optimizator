package com.example.timofey.mapnavigationoptimizator.points.all

import com.example.timofey.mapnavigationoptimizator.GoogleApiModel
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * Created by Timofey on 13.02.2018.
 */
class PointsPresenter(private val model: Points.Model) : Points.Presenter {

    lateinit var view: Points.View

    override fun bind(view: Points.View) {
        this.view = view
        model.getPointList()
                .subscribe(Consumer(view::setPoints))
    }

    override fun unbind(view: Points.View) {

    }

    override fun onButtonClicked() {
        model.getPointList()
                .flatMap { list -> model.getDimensionsMatrix(list) }
                .subscribeOn(Schedulers.newThread())
                .subscribe()
    }
}