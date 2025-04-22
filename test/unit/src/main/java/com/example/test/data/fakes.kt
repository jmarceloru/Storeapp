package com.example.test.data

import com.example.data.ProductsRepositoryService
import com.example.data.datasources.ProductLocalDataService
import com.example.data.datasources.ProductRemoteDataService
import com.example.data.models.CategoryData
import com.example.data.models.ProductData
import com.example.test.unit.sampleCategoriesData
import com.example.test.unit.sampleProductsData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update


fun buildProductRepositoryService(
    localDataCategories: List<CategoryData> = emptyList(),
    localDataProducts: List<ProductData> = emptyList(),
    remoteDataProducts: List<ProductData> = emptyList(),
    remoteDataCategories: List<CategoryData> = emptyList()
): ProductsRepositoryService {
    val localDataSource =
        FakeLocalDataSource().apply {
            inMemoryCategories.value = localDataCategories
            inMemoryProducts.value = localDataProducts
        }
    val remoteDataSource = FakeRemoteDataSource(
        categories = remoteDataCategories,
        products = remoteDataProducts
    )

    return ProductsRepositoryService(remoteDataSource,localDataSource)
}

class FakeLocalDataSource : ProductLocalDataService {

    val inMemoryCategories = MutableStateFlow<List<CategoryData>>(emptyList())
    val inMemoryProducts = MutableStateFlow<List<ProductData>>(emptyList())

    override fun fetchCategories(): Flow<List<CategoryData>> = inMemoryCategories

    override suspend fun saveCategories(categories: List<CategoryData>) {
        inMemoryCategories.value = categories
    }

    override fun fetchProductsByCategory(category: String): Flow<List<ProductData>> =
        inMemoryProducts.map { products ->
            products.filter { product ->
                product.category == category
            }
        }

    override suspend fun saveProducts(products: List<ProductData>) {
        inMemoryProducts.value = products
    }

    override suspend fun fetchProductByIdProduct(idProduct: Int): ProductData? =
        inMemoryProducts.value.find { product ->
            product.id == idProduct
        }

    override suspend fun saveProduct(product: ProductData) {
        inMemoryProducts.update {
            it + product
        }
    }
}

class FakeRemoteDataSource(
    private val categories: List<CategoryData> = sampleCategoriesData("jewerly", "electronics"),
    private val products: List<ProductData> = sampleProductsData(1, 2, 3, 4)
) : ProductRemoteDataService {

    override suspend fun fetchCategories(): List<CategoryData> = categories

    override suspend fun fetchProductsByCategory(category: String): List<ProductData> = products

    override suspend fun fetchProductById(idProduct: Int): ProductData =
        products.first { it.id == idProduct }
}