package com.example.storeapp.data.remote

import com.example.storeapp.data.remote.models.ProductResult
import com.example.storeapp.data.remote.retrofit.ProductClient
import com.example.storeapp.data.remote.retrofit.ProductService

class ProductRemoteDataSource(
    private val productService: ProductService
) : ProductRemoteDataService {

    override suspend fun fetchCategories(): List<String> =
        productService.fetchCategories()

    override suspend fun fetchProductsByCategory(category: String): List<ProductResult> =
        productService.fetchProductsByCategory(category)

    override suspend fun fetchProductById(idProduct: Int): ProductResult =
        productService.fetchProductById(idProduct)
}