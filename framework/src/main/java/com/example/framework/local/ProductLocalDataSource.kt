package com.example.framework.local

import com.example.data.datasources.ProductLocalDataService
import com.example.data.models.CategoryData
import com.example.data.models.ProductData
import com.example.framework.local.database.CategoryDao
import com.example.framework.local.database.ProductDao
import com.example.framework.local.entities.CategoryEntity
import com.example.framework.local.entities.toCategoryData
import com.example.framework.local.entities.toProductData
import com.example.framework.local.entities.toProductEntitty
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class ProductLocalDataSource(
    private val categoryDao: CategoryDao,
    private val productDao: ProductDao
) : ProductLocalDataService {

    override fun fetchCategories(): Flow<List<CategoryData>> =
        categoryDao.getCategories().map { categories ->
            categories.map {
                it.toCategoryData()
            }
        }

    override suspend fun saveCategories(categories: List<CategoryData>) =
        categoryDao.insertCategories(
            categories.map {
                CategoryEntity(it.title)
            }
        )

    override fun fetchProductsByCategory(category: String): Flow<List<ProductData>> =
        productDao.getProductByCategory(category).map { products ->
            products.map {
                it.toProductData()
            }
        }

    override suspend fun saveProducts(products: List<ProductData>) = productDao.insertProducts(
        products.map {
            it.toProductEntitty()
        }
    )

    override suspend fun fetchProductByIdProduct(idProduct: Int): ProductData? =
        productDao.getProductById(idProduct)?.toProductData()

    override suspend fun saveProduct(product: ProductData) = productDao.insertProduct(product.toProductEntitty())
}