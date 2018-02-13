package com.example.timofey.mapnavigationoptimizator.points.all

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.timofey.mapnavigationoptimizator.R
import com.example.timofey.mapnavigationoptimizator.database.Point
import kotlinx.android.synthetic.main.i_point.view.*

/**
 * Created by Timofey on 13.02.2018.
 */
class PointsAdapter(var list : List<Point>) : RecyclerView.Adapter<PointsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.i_point, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindHolder(list[position])
    }


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bindHolder(point: Point) = with(point) {
            itemView.point_name.setText(name)
        }
    }
}