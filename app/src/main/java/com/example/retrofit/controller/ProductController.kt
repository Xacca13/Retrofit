package com.example.retrofit.controller

import com.example.retrofit.dao.IProduct
import com.example.retrofit.model.product.Product
import com.example.retrofit.model.product.Products

class ProductController : IProduct {
    private var controller: IProduct = RetrofitController().create().create(IProduct::class.java)

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