package com.example.xiaoguoweather.utils

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by 黄家三少 on 2017/10/8.
 */
object HttpUtil {
    fun sendOkHttpRequest(address: String, callback: Callback) {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url(address).build()
        okHttpClient.newCall(request).enqueue(callback)
    }
}