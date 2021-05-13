package com.weather.app.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.geocoder.*
import com.weather.app.databinding.ActivityMainBinding
import com.weather.app.ui.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestLocationInfo()

        model.weatherModel.observe(this) { result ->
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

        val geocodeSearch = GeocodeSearch(this)
        binding.button.setOnClickListener {
//            binding.textView.text = "查询中..."
//            model.changeCity(binding.cityInput.text.toString())
            val res = LatLonPoint(25.28, 111.33)
            val query = RegeocodeQuery(res, 200f, GeocodeSearch.AMAP)
            geocodeSearch.getFromLocationAsyn(query)
        }


        geocodeSearch.setOnGeocodeSearchListener(object : GeocodeSearch.OnGeocodeSearchListener {
            override fun onRegeocodeSearched(p0: RegeocodeResult?, p1: Int) {
                if (p1 == 1000) {
                    p0?.apply {
                        println(regeocodeAddress.district)  // 区或者县城
                    }
                }
            }

            override fun onGeocodeSearched(p0: GeocodeResult?, p1: Int) {

            }
        })
    }


    private fun requestLocationInfo() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        } else {
            handleLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun handleLocation() {
        val manager = getSystemService(LOCATION_SERVICE) as LocationManager
        val listener = LocationListener() {
            println(it.longitude)
        }

        val info = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        Toast.makeText(this, "${info==null}", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    handleLocation()
                }
            }
        }
    }
}

