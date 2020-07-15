package com.example.rma2.data.model.remote

import com.squareup.moshi.Json
import java.math.BigDecimal

class Current(
    @field:Json(name = "temp_c")
    val temp: Double,

    @field:Json(name = "wind_kph")
    val wind: Double,

    val condition: Condition,

    @field:Json(name = "uv")
    val uv: Double


) {

}