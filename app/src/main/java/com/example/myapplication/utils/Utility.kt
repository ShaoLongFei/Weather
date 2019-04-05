package com.example.xiaoguoweather.utils

import android.text.TextUtils
import com.example.myapplication.bean.Weather
import com.example.xiaoguoweather.databean.City
import com.example.xiaoguoweather.databean.County
import com.example.xiaoguoweather.databean.Province
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by 黄家三少 on 2017/10/6.
 */
object Utility {
    //解析和处理服务器返回的省级数据
    fun handleProviceReaponse(response: String): List<Province> {
        val provinces = mutableListOf<Province>()
        if (!TextUtils.isEmpty(response)) {
            val allProvinces = JSONArray(response)
            for (i in 0..allProvinces.length() - 1) {
                val provinceObject = allProvinces.optJSONObject(i)
                val province = Province(provinceName = provinceObject.optString("name"),
                        provinceCode = provinceObject.optString("id"))
                provinces.add(province)
            }
        }
        return provinces
    }

    //解析和处理服务器返回的市级数据
    fun handleCityReaponse(response: String, provinceCode: String): List<City> {
        val cities = mutableListOf<City>()
        if (!TextUtils.isEmpty(response)) {
            val allCities = JSONArray(response)
            for (i in 0..allCities.length() - 1) {
                val cityObject = allCities.optJSONObject(i)
                val city = City(cityName = cityObject.optString("name"),
                        cityCode = cityObject.optString("id"),
                        provinceCode = provinceCode)
                cities.add(city)
            }
        }
        return cities
    }

    //解析和处理服务器返回的县级数据
    fun handleCountyReaponse(response: String, cityCode: String): List<County> {
        val counties = mutableListOf<County>()
        if (!TextUtils.isEmpty(response)) {
            val allCounties = JSONArray(response)
            for (i in 0..allCounties.length() - 1) {
                val countyObject = allCounties.optJSONObject(i)
                val county = County(countyNmae = countyObject.optString("name"),
                        countyCode = countyObject.optString("id"),
                        cityCode = cityCode)
                counties.add(county)
            }
        }
        return counties
    }

    //将返回的JSON数据解析成Weather实体类
    fun handleWeatherReaponse(response: String): Weather {
        val jsonObject = JSONObject(response)
        val optJSONArray = jsonObject.optJSONArray("HeWeather")
        val weatherContent = optJSONArray.optJSONObject(0).toString()
        return Gson().fromJson(weatherContent, Weather::class.java)
    }

}