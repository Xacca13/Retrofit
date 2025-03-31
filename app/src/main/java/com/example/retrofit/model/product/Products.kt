package com.example.retrofit.model.product

data class Products(
    val products: List<Product>,
    val total: Int,
    val slip: Int,
    val limit: Int
)
