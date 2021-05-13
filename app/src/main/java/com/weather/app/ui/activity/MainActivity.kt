package com.weather.app.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.weather.app.databinding.ActivityMainBinding
import com.weather.app.ui.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*model.weatherModel.observe(this) { result ->
            result.getOrNull()?.run {
                if (data.isNotEmpty()) {
                    val sb = StringBuilder().apply {
                        append("城市：${city} \n")
                        append("当前温度：${data[0].tem}℃ \n")
                        data.forEach {
                            append("${it.date}：${it.wea}。温度：${it.temMin}℃~${it.temMax}℃ \n")
                        }
                    }
                    binding.textView.text = sb.toString()
                }
            }
        }

        binding.button.setOnClickListener {
            binding.textView.text = "查询中..."
            model.changeCity(binding.cityInput.text.toString())
        }*/

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.map
    }
}