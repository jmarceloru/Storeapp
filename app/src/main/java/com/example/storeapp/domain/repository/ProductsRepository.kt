package com.example.storeapp.domain.repository

import com.example.storeapp.domain.models.Category
import com.example.storeapp.domain.models.Product


interface ProductsRepository {
    suspend fun fetchCategories():List<Category>
    suspend fun fetchProductsByCategory(category: String):List<Product>
}