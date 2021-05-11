package com.weather.app.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.app.logic.model.CityWeatherModel
import com.weather.app.logic.network.api.CityWeatherApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val weatherModel = MutableLiveData<CityWeatherModel>()

    fun getCityWeather(city: String) {
        val cityTrim = city.trim()
        if (cityTrim.isEmpty()) {
            return
        }
        viewModelScope.launch {
            delay(500)
            val res = CityWeatherApi.get(cityTrim)
            if (!res.cityId.isNullOrBlank()) {
                weatherModel.value = res
            }
        }
    }
}