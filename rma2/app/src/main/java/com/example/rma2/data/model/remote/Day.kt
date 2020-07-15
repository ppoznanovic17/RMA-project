package com.example.rma2.data.model.remote

import android.app.Person
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Types
import java.math.BigDecimal


class Day(

    @field:Json(name = "maxtemp_c")
    val maxTemp: Double,

    @field:Json(name = "mintemp_c")
    val minTemp: Double,

    @field:Json(name = "avgtemp_c")
    val avgTemp: Double,

    @field:Json(name = "maxwind_kph")
    val maxWind: Double,


    val uv: Double,

    val condition: Condition


) {



    override fun toString(): String {
        return maxTemp.toString() + " " + minTemp + " " + avgTemp + " " + maxWind + " " + condition.icon  + " " +  uv
    }
}