package com.example.rma2.data.datasources.remote.adapter

import com.example.rma2.data.model.remote.ForecastDay
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson


class ArrayAdapter {

    @ToJson
    fun arrayListToJson(list: ArrayList<ForecastDay>): List<ForecastDay> = list

    @FromJson
    fun arrayListFromJson(list: List<ForecastDay>): ArrayList<ForecastDay> = ArrayList(list)
}