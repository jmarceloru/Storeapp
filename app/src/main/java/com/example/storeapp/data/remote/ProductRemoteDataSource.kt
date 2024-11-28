package com.example.storeapp.data.remote

import com.example.storeapp.data.models.ProductResult
import com.example.storeapp.data.remote.retrofit.ProductClient

class ProductRemoteDataSource : ProductRemoteDataService {

    override suspend fun fetchCategories(): List<String> {
        return ProductClient.instance.fetchCategories()
    }

    override suspend fun fetchProductsByCategory(category: String): List<ProductResult> {
       return ProductClient.instance.fetchProductsByCategory(category)
    }

}