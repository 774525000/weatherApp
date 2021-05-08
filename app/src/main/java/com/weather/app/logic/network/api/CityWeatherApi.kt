package com.weather.app.logic.network.api

import com.weather.app.logic.network.ApiUtil
import com.weather.app.logic.network.service.CityWeatherService
import com.weather.app.logic.model.CityWeatherModel
import retrofit2.await

object CityWeatherApi {
    private val api = ApiUtil.create<CityWeatherService>()

    suspend fun get(city: String): CityWeatherModel {
        return api.get(city).await()
    }
}