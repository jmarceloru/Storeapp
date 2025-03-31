package com.example.storeapp.data.local

import com.example.storeapp.data.local.database.CategoryDao
import com.example.storeapp.data.local.database.ProductDao
import com.example.storeapp.data.local.entities.Category
import com.example.storeapp.data.local.entities.Product
import kotlinx.coroutines.flow.Flow

class ProductLocalDataSource(
    private val categoryDao: CategoryDao,
    private val productDao: ProductDao
) : ProductLocalDataService {

    override fun fetchCategories(): Flow<List<Category>> = categoryDao.getCategories()

    override suspend fun saveCategories(categories: List<Category>) = categoryDao.insertCategories(categories)

    override fun fetchProductsByCategory(category: String): Flow<List<Product>> = productDao.getProductByCategory(category)

    override suspend fun saveProducts(products: List<Product>) = productDao.insertProducts(products)

    override suspend fun fetchProductByIdProduct(idProduct: Int): Product? = productDao.getProductById(idProduct)

    override suspend fun saveProduct(product: Product) = productDao.insertProduct(product)
}