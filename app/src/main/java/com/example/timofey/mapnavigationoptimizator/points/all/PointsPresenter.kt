package com.example.timofey.mapnavigationoptimizator.points.all

import io.reactivex.functions.Consumer

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}