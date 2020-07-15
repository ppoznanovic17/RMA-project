package com.example.rma2.data.repositories

import com.example.rma2.data.model.Resource
import com.example.rma2.data.model.local.WeatherEntity
import io.reactivex.Observable

interface WeatherRepository {

    fun fetchAll(city: String, days: Int): Observable<Resource<Unit>>

    fun getAll(city:String): Observable<List<WeatherEntity>>

}