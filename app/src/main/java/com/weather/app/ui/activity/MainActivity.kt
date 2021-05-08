package com.weather.app.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weather.app.databinding.ActivityMainBinding
import com.weather.app.logic.network.api.CityWeatherApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.setOnClickListener {
            GlobalScope.launch {
                val res = CityWeatherApi.get("广州")
                println(res)
            }
        }
    }
}