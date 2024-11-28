package com.example.storeapp.data

import com.example.storeapp.R
import com.example.storeapp.data.models.Categories
import com.example.storeapp.data.models.toDomainProduct
import com.example.storeapp.data.remote.ProductRemoteDataService
import com.example.storeapp.domain.models.Category
import com.example.storeapp.domain.models.Product
import com.example.storeapp.domain.repository.ProductsRepository

class ProductsServiceRepository(
    private val productRemoteDataService: ProductRemoteDataService
) : ProductsRepository {
    override suspend fun fetchCategories(): List<Category> {
        val categories = productRemoteDataService.fetchCategories()
        val categoryList = categories.map {
            Category(
                it,
                getImage(it)
            )
        }
        return categoryList
    }

    override suspend fun fetchProductsByCategory(category: String): List<Product> {
        val products = productRemoteDataService.fetchProductsByCategory(category = category)
        val productList = products.map {
            it.toDomainProduct()
        }
        return productList
    }

    private fun getImage(title: String): Int =
        when (title) {
            Categories.Electronics.title -> R.drawable.electronics
            Categories.Jewerly.title -> R.drawable.jewelery
            Categories.MenClothing.title -> R.drawable.menclothes
            Categories.WomenClothing.title -> R.drawable.womenclotehs
            else -> R.drawable.notfound
        }
}

