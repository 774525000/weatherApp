package com.weather.app.logic.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class CityWeatherModel(
    val date: Date,
    @SerializedName("cityid") val cityId: String,
    val city: String,
    val country: String,
    @SerializedName("wea") val weather: String,
    val tem: Float,
    @SerializedName("tem2") val temMin: Float,
    @SerializedName("tem1") val temMax: Float,
    @SerializedName("air_tips") val airTips: String,
)