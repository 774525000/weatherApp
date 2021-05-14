package com.weather.app.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.geocoder.*
import com.weather.app.databinding.ActivityMainBinding
import com.weather.app.ui.viewModel.MainViewModel
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()
    private lateinit var manager: LocationManager
    private lateinit var geocodeSearch: GeocodeSearch
    private lateinit var listener: LocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**  初始化数据 ✌️✌️✌️**/
        initData()

        /**  请求地理位置 ✌️✌️✌️**/
        requestLocationInfo()

        /**  观察livedata ✌️✌️✌️**/
        observeLiveData()

        /**  绑定各种事件 ✌️✌️✌️**/
        bindEventListener()

        /**  通过经纬度获取位置信息 ✌️✌️✌️**/
        listenLocationChange()
    }

    private fun initData() {
        manager = getSystemService(LOCATION_SERVICE) as LocationManager
        geocodeSearch = GeocodeSearch(this)
    }

    private fun listenLocationChange() {
        geocodeSearch.setOnGeocodeSearchListener(object : GeocodeSearch.OnGeocodeSearchListener {
            override fun onRegeocodeSearched(p0: RegeocodeResult?, p1: Int) {
                if (p1 == 1000) {
                    p0?.apply {
                        binding.textView.text = regeocodeAddress.district
                        manager.removeUpdates(listener)
                    }
                }
            }

            override fun onGeocodeSearched(p0: GeocodeResult?, p1: Int) {

            }
        })
    }

    private fun observeLiveData() {
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
    }

    private fun bindEventListener() {
        binding.button.setOnClickListener {
            binding.textView.text = "查询中..."
            model.changeCity(binding.cityInput.text.toString())
        }
    }

    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationInfo() {
        if (checkPermission()) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        } else {
            handleLocationInfo()
        }
    }

    private fun handleLocationInfo() {
        if (checkPermission())
            return

        listener = LocationListener {
            val res = LatLonPoint(it.latitude, abs(it.longitude))
            val query = RegeocodeQuery(res, 200f, GeocodeSearch.AMAP)
            geocodeSearch.getFromLocationAsyn(query)
        }

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, listener)
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
                    handleLocationInfo()
                }
            }
        }
    }
}

