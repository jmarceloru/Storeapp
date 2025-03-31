package com.example.storeapp.data.remote.retrofit

import com.example.storeapp.data.remote.models.ProductResult
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET("products/categories")
    suspend fun fetchCategories() : List<String>

    @GET("products/category/{title}")
    suspend fun fetchProductsByCategory(@Path("title")categoryTitle: String) : List<ProductResult>

    @GET("products/{id}")
    suspend fun fetchProductById(@Path("id")productId: Int): ProductResult
}