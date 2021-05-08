package com.weather.app.logic.network.service

import com.weather.app.App
import com.weather.app.logic.model.CityWeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface CityWeatherService {
    @GET("?version=${App.version}&appid=${App.appId}&appsecret=${App.appSecret}")
    fun get(@Query("city") city: String): Call<CityWeatherModel>
}