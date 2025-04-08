package com.example.domain.usecases

import com.example.domain.models.Product
import com.example.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

class FetchProductsByCategoryUseCase(
    private val productsRepository: ProductsRepository
) {
    operator fun invoke(category: String): Flow<List<Product>> = productsRepository.fetchProductsByCategory(category)
}