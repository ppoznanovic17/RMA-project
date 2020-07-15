package com.example.rma2.presentation.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rma2.data.model.local.WeatherEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_weather.view.*
import java.math.RoundingMode
import java.text.SimpleDateFormat

class WeatherListViewHolder(override val containerView: View,
                            private val onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {


    init {
        containerView.setOnClickListener {
            onItemClicked.invoke(adapterPosition)
        }

    }

    fun bind(w: WeatherEntity){
        val rounded = w.avgTemp.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()
        containerView.tempTv.text = rounded.toString() + "\u2103"

        containerView.cityTv.text = w.name

        val sdf = SimpleDateFormat("dd.MM.yyyy")
        val date: String = sdf.format(w.date)
        containerView.dateTv.text = date




    }

}