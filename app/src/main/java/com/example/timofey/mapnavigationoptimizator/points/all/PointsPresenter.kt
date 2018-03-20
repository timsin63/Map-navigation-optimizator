package com.example.timofey.mapnavigationoptimizator.points.all

import com.example.timofey.mapnavigationoptimizator.GoogleApiModel
import com.example.timofey.mapnavigationoptimizator.core.RoutingCalculator
import com.example.timofey.mapnavigationoptimizator.database.Point
import com.example.timofey.mapnavigationoptimizator.utils.Mappers
import io.reactivex.Completable
import io.reactivex.functions.Action
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
                .flatMapCompletable { list ->
                    model
                            .getDimensionsMatrix(list)
                            .map { pointsRsp -> Mappers.toRoutingMatrix(pointsRsp) }
                            .flatMapCompletable { routingMatrix ->
                                Completable.fromAction(Action {
                                    val arr = RoutingCalculator().calculate(routingMatrix)
                                    view.openMapWithResult(arr)
                                })
                            }
                }
                .subscribeOn(Schedulers.newThread())
                .subscribe()
    }
}