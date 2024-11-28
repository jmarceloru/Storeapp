package com.example.storeapp.data.remote

import com.example.storeapp.data.models.ProductResult

interface ProductRemoteDataService {
    suspend fun fetchCategories(): List<String>
    suspend fun fetchProductsByCategory(category:String): List<ProductResult>
}