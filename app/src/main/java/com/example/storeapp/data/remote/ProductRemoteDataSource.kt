package com.example.storeapp.data.remote

import com.example.storeapp.data.models.ProductResult
import com.example.storeapp.data.remote.retrofit.ProductClient

class ProductRemoteDataSource : ProductRemoteDataService {

    override suspend fun fetchCategories(): List<String> =
         ProductClient.instance.fetchCategories()

    override suspend fun fetchProductsByCategory(category: String): List<ProductResult> =
        ProductClient.instance.fetchProductsByCategory(category)

    override suspend fun fetchProductById(idProduct: Int): ProductResult =
         ProductClient.instance.fetchProductById(idProduct)
}