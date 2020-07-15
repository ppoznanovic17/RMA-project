package com.example.rma2.data.datasources.remote

import com.example.rma2.data.model.remote.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {


    @GET("forecast.json")
    fun getWeather(@Query("q") city:String, @Query("days") days:Int): Observable<WeatherResponse>

}