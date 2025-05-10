package com.example.retrofit.controller

import com.example.retrofit.dao.IProduct
import com.example.retrofit.model.product.Product
import com.example.retrofit.model.product.Products
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val url: String = "https://dummyjson.com/"

class ProductController : IProduct {
    private var retrofit: Retrofit
    private var controller: IProduct
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
        controller = retrofit.create(IProduct::class.java)
    }

    override suspend fun getProductById(id: Int): Product {
        return controller.getProductById(id)
    }

    override suspend fun getAllProductsByName(q: String): Products {
        return controller.getAllProductsByName(q)
    }

    override suspend fun getAllProduct(): Products {
        return controller.getAllProduct()
    }

    override suspend fun getFromUserProductById(token: String, id: Int): Product {
        return controller.getFromUserProductById(token, id)
    }

    override suspend fun getFromUserAllProductsByName(token: String, q: String): Products {
        return controller.getFromUserAllProductsByName(token, q)
    }

    override suspend fun getFromUserAllProduct(token: String): Products {
        return controller.getFromUserAllProduct(token)
    }
}