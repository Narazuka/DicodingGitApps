package com.dicoding.mysubmissionfd1.data.retrofit

import com.dicoding.mysubmissionfd1.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiConfig {
    companion object{
        fun getApiService(): ApiService{
            val autInterceptor = Interceptor {
                chain ->
                val req = chain.request()
                val requestHeaders = req.newBuilder()
                    .addHeader("Authorization", BuildConfig.API_KEY)
                    .build()
                chain.proceed(requestHeaders)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(autInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}