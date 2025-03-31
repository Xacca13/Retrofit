package com.example.retrofit.controller

import com.example.retrofit.dao.IAuth
import com.example.retrofit.model.request.AuthBody
import com.example.retrofit.model.user.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val url: String = "https://dummyjson.com/"

class AuthController: IAuth {

    private var retrofit: Retrofit
    private var controller: IAuth
    private var interceptor: HttpLoggingInterceptor
    private var client: OkHttpClient

    init {
        interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        controller = retrofit.create(IAuth::class.java)
    }

    override suspend fun getUserInfo(body: AuthBody): User {
        return controller.getUserInfo(body)
    }
}