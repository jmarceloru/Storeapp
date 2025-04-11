package com.example.data

import com.example.data.datasources.ProductLocalDataService
import com.example.data.datasources.ProductRemoteDataService
import com.example.data.models.ProductData
import com.example.data.models.RatingData
import com.example.data.models.toDomain
import com.example.domain.models.Category
import com.example.domain.repository.ProductsRepository
import com.example.domain.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach


internal class ProductsRepositoryService(
    private val productRemoteDataService: ProductRemoteDataService,
    private val productLocalDataService: ProductLocalDataService
) : ProductsRepository {
    override fun fetchCategories(): Flow<List<Category>> =
        productLocalDataService.fetchCategories().onEach { categories ->
            if (categories.isEmpty()) {
                val categoryData = productRemoteDataService.fetchCategories()
                productLocalDataService.saveCategories(categoryData)
            }
        }.map { it.map { c -> c.toDomain() } }

    override fun fetchProductsByCategory(category: String): Flow<List<Product>> =
        productLocalDataService.fetchProductsByCategory(category).onEach { products ->
            if (products.isEmpty()) {
                val producstEntity =
                    productRemoteDataService.fetchProductsByCategory(category)
                productLocalDataService.saveProducts(producstEntity)
            }
        }.map {
            it.map { p -> p.toDomain() }
        }

    override suspend fun fetchProductById(idProduct: Int): Product {
        val product =
            productLocalDataService.fetchProductByIdProduct(idProduct).takeIf { it != null }
                ?: productRemoteDataService.fetchProductById(idProduct)
                    .also { product ->
                        productLocalDataService.saveProduct(product)
                    }

        return product.toDomain()
    }

    override suspend fun updateProduct(product: Product) {
        val rating = RatingData(
            count = product.rating.count,
            rate = product.rating.rate
        )
        val productData = ProductData(
            category = product.category,
            description = product.description,
            id = product.id,
            image = product.image,
            price = product.price,
            rating = rating,
            title = product.title,
            favorite = product.favorite
        )
        productLocalDataService.saveProduct(productData)
    }
}

