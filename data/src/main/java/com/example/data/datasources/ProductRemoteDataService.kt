package com.example.data.datasources

import com.example.data.models.CategoryData
import com.example.data.models.ProductData

interface ProductRemoteDataService {
    suspend fun fetchCategories(): List<CategoryData>
    suspend fun fetchProductsByCategory(category:String): List<ProductData>
    suspend fun fetchProductById(idProduct: Int): ProductData
}