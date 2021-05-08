package com.weather.app.logic.network.api

import com.weather.app.logic.network.ApiUtil
import com.weather.app.logic.network.service.CityWeatherService
import com.weather.app.logic.model.CityWeatherModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

object CityWeatherApi {
    private val api = ApiUtil.create<CityWeatherService>()

    suspend fun get(city: String): CityWeatherModel {
        return withContext(Dispatchers.IO) {
            api.get(city).await()
        }
    }
}