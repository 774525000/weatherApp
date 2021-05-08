package com.weather.app.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weather.app.databinding.ActivityMainBinding
import com.weather.app.logic.network.api.CityWeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                val res = CityWeatherApi.get("广州")
                println(1)
            }
            println(2)
        }
    }
}