package com.example.retrofit.dao



import com.example.retrofit.model.product.Product
import com.example.retrofit.model.product.Products
import retrofit2.http.GET
import retrofit2.http.Path

interface IProduct {
    @GET("product/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product

    @GET("products")
    suspend fun getAllProduct(): Products
}