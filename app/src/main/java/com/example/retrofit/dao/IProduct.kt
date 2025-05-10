package com.example.retrofit.dao



import com.example.retrofit.model.product.Product
import com.example.retrofit.model.product.Products
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface IProduct {
    @GET("product/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product

    @GET("products/search")
    suspend fun getAllProductsByName(@Query("q") q: String): Products

    @GET("products")
    suspend fun getAllProduct(): Products

    @Headers("Content-Type: application/json")
    @GET("auth/product/{id}")
    suspend fun getFromUserProductById(@Header("Authorization") token: String, @Path("id") id: Int): Product

    @Headers("Content-Type: application/json")
    @GET("auth/products/search")
    suspend fun getFromUserAllProductsByName(@Header("Authorization") token: String, @Query("q") q: String): Products

    @Headers("Content-Type: application/json")
    @GET("auth/products")
    suspend fun getFromUserAllProduct(@Header("Authorization") token: String): Products
}