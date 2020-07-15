package com.example.rma2.data.model.remote

import com.squareup.moshi.Json

class Forecast(
    @field:Json(name = "forecastday")
    val forecastDay: ArrayList<ForecastDay>
) {



}