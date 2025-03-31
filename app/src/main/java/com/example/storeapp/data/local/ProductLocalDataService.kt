package com.example.storeapp.data.local

import com.example.storeapp.data.local.entities.Category
import com.example.storeapp.data.local.entities.Product
import kotlinx.coroutines.flow.Flow

interface ProductLocalDataService {
    fun fetchCategories(): Flow<List<Category>>
    suspend fun saveCategories(categories: List<Category>)
    fun fetchProductsByCategory(category: String): Flow<List<Product>>
    suspend fun saveProducts(products: List<Product>)
    suspend fun fetchProductByIdProduct(idProduct: Int): Product?
    suspend fun saveProduct(product: Product)
}