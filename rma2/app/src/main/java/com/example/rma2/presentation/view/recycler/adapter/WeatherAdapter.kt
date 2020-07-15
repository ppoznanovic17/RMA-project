package com.example.rma2.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.rma2.R
import com.example.rma2.data.model.local.WeatherEntity
import com.example.rma2.presentation.view.recycler.diff.WeatherDiffCallback
import com.example.rma2.presentation.view.recycler.viewholder.WeatherListViewHolder

class WeatherAdapter(
    private val onItemClicked: (WeatherEntity) -> Unit
): ListAdapter<WeatherEntity, WeatherListViewHolder>(WeatherDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.item_weather, parent, false)
        return WeatherListViewHolder(containerView) {
            val weather = getItem(it)
            onItemClicked.invoke(weather)
        }
    }

    override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
        val weather = getItem(position)
        holder.bind(weather)
    }
}