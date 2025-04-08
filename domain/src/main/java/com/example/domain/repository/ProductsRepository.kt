package com.example.domain.repository

import com.example.domain.models.Category
import com.example.domain.models.Product
import kotlinx.coroutines.flow.Flow


interface ProductsRepository {
    fun fetchCategories(): Flow<List<Category>>
    fun fetchProductsByCategory(category: String):Flow<List<Product>>
    suspend fun fetchProductById(idProduct:Int): Product
    suspend fun updateProduct(product: Product)
}