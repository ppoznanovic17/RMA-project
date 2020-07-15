package com.example.rma2.data.model.remote

import com.squareup.moshi.Json
import java.util.*

class ForecastDay(
    val date: Date,

    val day: Day

) {

    override fun toString(): String {
        return "(" + date.toString() + " " + day.condition + " " + day.maxWind + " " + day.avgTemp + " " + day.minTemp + " " + day.maxTemp + " " + day.uv + ")"
    }

}