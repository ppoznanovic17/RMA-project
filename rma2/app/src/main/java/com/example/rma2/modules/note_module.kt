package com.example.rma2.modules


import com.example.rma2.data.datasources.local.WeatherDao
import com.example.rma2.data.datasources.local.WeatherDataBase
import com.example.rma2.data.datasources.remote.WeatherService
import com.example.rma2.data.repositories.WeatherRepository
import com.example.rma2.data.repositories.WeatherRepositoryImpl
import com.example.rma2.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val noteModule = module {


    viewModel { WeatherViewModel ( weatherRepository = get()) }

    single<WeatherRepository> { WeatherRepositoryImpl(remoteDateResource = get(), localDataResource = get()) }

    single<WeatherDao> { get<WeatherDataBase>().getWeatherDao() }

    single<WeatherService> { create(retrofit = get())}



}