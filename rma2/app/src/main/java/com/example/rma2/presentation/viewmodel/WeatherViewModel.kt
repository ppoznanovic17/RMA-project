package com.example.rma2.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rma2.data.model.Resource
import com.example.rma2.data.model.local.WeatherEntity
import com.example.rma2.data.repositories.WeatherRepository
import com.example.rma2.presentation.contract.WeatherContract
import com.example.rma2.presentation.view.states.WeatherState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class WeatherViewModel(private val weatherRepository: WeatherRepository): ViewModel(), WeatherContract.ViewModel {

    override val weatherState: MutableLiveData<WeatherState> = MutableLiveData()
    override val current: MutableLiveData<WeatherEntity> = MutableLiveData()

    private val subscriptions = CompositeDisposable()


    override fun fetchWeather(city: String, days: Int) {
        val subscription  = weatherRepository
            .fetchAll(city, days)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> weatherState.value = WeatherState.Loading
                        is Resource.Success -> weatherState.value = WeatherState.DataFetched
                        is Resource.Error -> weatherState.value = WeatherState.Error("Greska pri preuzimanju!!")
                    }
                },
                {

                    weatherState.value = WeatherState.Error("Greska pri preuzimanju...")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAll(city:String) {
        val subscription = weatherRepository
            .getAll(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    weatherState.value = WeatherState.Success(it.subList(1, it.size))
                    current.value = it[0]
                },
                {
                    weatherState.value = WeatherState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}