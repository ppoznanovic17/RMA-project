package com.example.rma2.presentation.view.states

import com.example.rma2.data.model.local.WeatherEntity


sealed class WeatherState {

    object Loading: WeatherState()
    object DataFetched: WeatherState()
    data class Success(val weatherEntity: List<WeatherEntity>): WeatherState()
    data class Error(val message: String): WeatherState()
}