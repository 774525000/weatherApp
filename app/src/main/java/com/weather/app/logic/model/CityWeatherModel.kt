package com.weather.app.logic.model

import com.google.gson.annotations.SerializedName
import java.time.format.DateTimeFormatter
import java.util.*

data class CityWeatherModel(
    @SerializedName("update_time") val updateTime: String,
    @SerializedName("cityid") val cityId: String?,
    val city: String,
    val country: String,
    val data: List<Weather>
) {
    data class Weather(
        val date: String,
        val day: String,
        val wea: String,
        val week: String,
        val tem: Int,
        @SerializedName("tem2") val temMin: Int,
        @SerializedName("tem1") val temMax: Int,
        @SerializedName("air_tips") val airTips: String
    )
}