package com.training.goldmarket.data.remote.interceptor

import android.util.Log
import com.training.goldmarket.data.preference.SharedPreference
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthTokenInterceptor @Inject constructor(private val sharedPref: SharedPreference) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        Log.d("Interceptor", "intercept: ${originalRequest.url()}")
        if (!originalRequest.url().toString().contains("login")) {
            Log.d("Interceptor", "intercept:")
            val requestBuilder = originalRequest.newBuilder()
                .header("Authorization", sharedPref.retrieve("token")?: "Token")
            val request = requestBuilder.build()
            return chain.proceed(request)
        }
        return chain.proceed(originalRequest)
    }

}