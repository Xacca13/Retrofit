package com.example.retrofit.controller

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL: String = "https://dummyjson.com/"

class RetrofitController {
    fun create(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit
    }

}