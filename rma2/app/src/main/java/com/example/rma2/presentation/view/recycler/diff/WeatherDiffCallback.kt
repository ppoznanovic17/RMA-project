package com.example.rma2.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.rma2.data.model.local.WeatherEntity

class WeatherDiffCallback: DiffUtil.ItemCallback<WeatherEntity>() {
    override fun areItemsTheSame(oldItem: WeatherEntity, newItem: WeatherEntity): Boolean {
       return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WeatherEntity, newItem: WeatherEntity): Boolean {
        return oldItem.avgTemp == newItem.avgTemp &&
                oldItem.date == newItem.date &&
                oldItem.lat == newItem.lat &&
                oldItem.lon == newItem.lon &&
                oldItem.maxTemp == newItem.maxTemp &&
                oldItem.maxWind == newItem.maxWind &&
                oldItem.minTemp == newItem.minTemp &&
                oldItem.name == newItem.name &&
                oldItem.uv == newItem.uv
    }
}