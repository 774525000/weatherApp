package com.weather.app.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.weather.app.logic.network.api.CityWeatherApi

class MainViewModel : ViewModel() {

    private val city = MutableLiveData<String>()

    val weatherModel = Transformations.switchMap(city) {
        CityWeatherApi.get(it)
    }

    fun changeCity(city_: String) {
        city.value = city_
    }
}