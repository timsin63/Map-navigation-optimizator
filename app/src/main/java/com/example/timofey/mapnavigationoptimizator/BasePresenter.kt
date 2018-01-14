package com.example.timofey.mapnavigationoptimizator

import android.view.View

/**
 * Created by Timofey on 25.12.2017.
 */
interface BasePresenter<T> {
    fun bind(view : T)

    fun unbind(view: T)
}