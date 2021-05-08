package com.weather.app.logic.network

import com.weather.app.App
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiUtil {
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(App.baseUrl)
        .addConverterFactory(GsonConverterFactory.create()).build()

    fun <T> create(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

    inline fun <reified T> create(): T = create(T::class.java)
}


