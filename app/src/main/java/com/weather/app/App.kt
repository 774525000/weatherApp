package com.weather.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class App : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        const val baseUrl = "https://v0.yiketianqi.com/api/"
        const val appId = "85277939"
        const val appSecret = "oZtko9LK"
        const val seven = "v9"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}