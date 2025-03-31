package com.example.retrofit.model.product

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Float,
    val discountPercentage: Float,
    val rating: Float,
    val stock: Int,
    val tags: List<String>,
    val brand: String,
    val sku: String,
    val weight: Int,
    val dimensions: Dimensions,
    val warrantyInformation: String,
    val shippingInformation: String,
    val availabilityStatus: String,
    val reviews: List<Reviews>,
    val returnPolicy: String,
    val minimumOrderQuantity: String,
    val meta: Meta,
    val thumbnail: String,
    val images: List<String>
)
