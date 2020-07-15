package com.example.rma2.data.repositories

import com.example.rma2.data.datasources.local.WeatherDao
import com.example.rma2.data.datasources.remote.WeatherService
import com.example.rma2.data.model.Resource
import com.example.rma2.data.model.local.WeatherEntity
import io.reactivex.Observable
import java.util.*

class WeatherRepositoryImpl(private val remoteDateResource: WeatherService,
                            private val localDataResource: WeatherDao): WeatherRepository {


    override fun fetchAll(city: String, days: Int): Observable<Resource<Unit>> {
        return remoteDateResource
            .getWeather(city, days)
            .map {


                val list = mutableListOf(
                    WeatherEntity(
                    0,
                    it.location.name,
                    it.location.lat,
                    it.location.lon,
                    Date(),
                    it.forecast.forecastDay[0].day.maxTemp,
                    it.forecast.forecastDay[0].day.minTemp,
                     it.current.temp,
                     it.current.wind,
                     it.current.uv
                )
                )
                for(i in 1..it.forecast.forecastDay.size-1){
                    list.add(
                        WeatherEntity(
                            0,
                            it.location.name,
                            it.location.lat,
                            it.location.lon,
                            it.forecast.forecastDay[i].date,
                            it.forecast.forecastDay[i].day.maxTemp,
                            it.forecast.forecastDay[i].day.minTemp,
                            it.forecast.forecastDay[i].day.avgTemp,
                            it.forecast.forecastDay[i].day.maxWind,
                            it.forecast.forecastDay[i].day.uv
                        )
                    )
                }
                localDataResource.deleteAndInsertAll(list)

            }.map {

                Resource.Success(Unit)
            }

    }

    override fun getAll(city: String): Observable<List<WeatherEntity>> {
        return localDataResource
            .getAll(city)
            .map {
                it.map {
                    WeatherEntity(
                        it.id,
                        it.name,
                        it.lat,
                        it.lon,
                        it.date,
                        it.maxTemp,
                        it.minTemp,
                        it.avgTemp,
                        it.maxWind,
                        it.uv)
                }
            }
    }



}