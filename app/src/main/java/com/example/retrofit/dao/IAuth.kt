package com.example.retrofit.dao

import com.example.retrofit.model.request.AuthBody
import com.example.retrofit.model.user.User
import retrofit2.http.Body
import retrofit2.http.POST

interface IAuth {

    @POST("auth/login")
    suspend fun getUserInfo(@Body body: AuthBody): User
}