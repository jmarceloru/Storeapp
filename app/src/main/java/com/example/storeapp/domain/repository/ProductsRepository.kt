package com.example.storeapp.domain.repository

import com.example.storeapp.domain.models.Category
import com.example.storeapp.domain.models.Product
import kotlinx.coroutines.flow.Flow


interface ProductsRepository {
    fun fetchCategories(): Flow<List<Category>>
    fun fetchProductsByCategory(category: String):Flow<List<Product>>
    suspend fun fetchProductById(idProduct:Int): Product
    suspend fun updateProduct(product: Product)
}