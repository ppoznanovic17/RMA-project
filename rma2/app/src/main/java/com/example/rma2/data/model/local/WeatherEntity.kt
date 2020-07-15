package com.example.rma2.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "weather")
data class WeatherEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var name: String,
    var lat: Double,
    var lon: Double,
    var date: Date,
    var maxTemp: Double,

    var minTemp: Double,

    var avgTemp: Double,

    var maxWind: Double,


    var uv: Double

):Serializable