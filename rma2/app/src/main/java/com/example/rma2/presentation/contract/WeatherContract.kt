package com.example.rma2.presentation.contract

import androidx.lifecycle.LiveData
import com.example.rma2.data.model.local.WeatherEntity
import com.example.rma2.presentation.view.states.WeatherState


interface WeatherContract {

    interface ViewModel {

        val weatherState: LiveData<WeatherState>
        val current: LiveData<WeatherEntity>

        fun fetchWeather(city: String, days: Int)


        fun getAll(city: String)
    }

}