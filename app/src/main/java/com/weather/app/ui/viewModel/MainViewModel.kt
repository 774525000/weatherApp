package com.weather.app.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.weather.app.logic.network.api.CityWeatherApi

class MainViewModel : ViewModel() {

    private val city = MutableLiveData<String>()

    val weatherModel = city.switchMap {
        CityWeatherApi.get(it)
    }

    fun changeCity(city_: String) {
        city.value = city_
    }
}