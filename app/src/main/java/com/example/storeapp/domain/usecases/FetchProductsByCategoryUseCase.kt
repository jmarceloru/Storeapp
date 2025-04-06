package com.example.storeapp.domain.usecases

import com.example.storeapp.domain.models.Product
import com.example.storeapp.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

class FetchProductsByCategoryUseCase(
    private val productsRepository: ProductsRepository
) {
    operator fun invoke(category: String): Flow<List<Product>> = productsRepository.fetchProductsByCategory(category)
}