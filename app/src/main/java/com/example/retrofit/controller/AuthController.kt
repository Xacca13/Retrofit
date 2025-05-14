package com.example.retrofit.controller

import com.example.retrofit.dao.IAuth
import com.example.retrofit.model.request.AuthBody
import com.example.retrofit.model.user.User
import retrofit2.Response

class AuthController: IAuth {
    private var controller: IAuth  = RetrofitController().create().create(IAuth::class.java)

    override suspend fun getUserInfo(body: AuthBody): Response<User> {
        return controller.getUserInfo(body)
    }
}