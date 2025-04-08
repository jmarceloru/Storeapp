package com.example.framework.remote

import com.example.data.datasources.ProductRemoteDataService
import com.example.data.models.CategoryData
import com.example.data.models.ProductData
import com.example.framework.remote.models.toProductData
import com.example.framework.remote.retrofit.ProductService

class ProductRemoteDataSource(
    private val productService: ProductService
) : ProductRemoteDataService {

    override suspend fun fetchCategories(): List<CategoryData> =
        productService.fetchCategories().map {
            CategoryData(it)
        }

    override suspend fun fetchProductsByCategory(category: String): List<ProductData> =
        productService.fetchProductsByCategory(category).map {
            it.toProductData()
        }

    override suspend fun fetchProductById(idProduct: Int): ProductData =
        productService.fetchProductById(idProduct).toProductData()
}

