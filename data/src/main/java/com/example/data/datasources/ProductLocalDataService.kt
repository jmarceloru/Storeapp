package com.example.data.datasources

import com.example.data.models.CategoryData
import com.example.data.models.ProductData
import kotlinx.coroutines.flow.Flow

interface ProductLocalDataService {
    fun fetchCategories(): Flow<List<CategoryData>>
    suspend fun saveCategories(categories: List<CategoryData>)
    fun fetchProductsByCategory(category: String): Flow<List<ProductData>>
    suspend fun saveProducts(products: List<ProductData>)
    suspend fun fetchProductByIdProduct(idProduct: Int): ProductData?
    suspend fun saveProduct(product: ProductData)
}