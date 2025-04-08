package com.example.domain.usecases

import com.example.domain.models.Product
import com.example.domain.repository.ProductsRepository

class FavoriteClickUseCase(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(product: Product) = productsRepository.updateProduct(product)
}