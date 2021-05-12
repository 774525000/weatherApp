package com.weather.app.logic.network.api

import androidx.lifecycle.liveData
import com.weather.app.logic.model.CityWeatherModel
import com.weather.app.logic.network.ApiUtil
import com.weather.app.logic.network.service.CityWeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import retrofit2.await
import java.lang.Exception

object CityWeatherApi {
    private val api = ApiUtil.create<CityWeatherService>()

    fun get(city: String) = liveData(Dispatchers.IO) {
        delay(500)
        val result = try {
            val res = api.get(city).await()
            Result.success(res)
        } catch (e: Exception) {
            Result.failure<CityWeatherModel>(e)
        }
        emit(result)
    }
}