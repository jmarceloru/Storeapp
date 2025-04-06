package com.example.storeapp.domain.usecases

import com.example.storeapp.domain.models.Product
import com.example.storeapp.domain.repository.ProductsRepository

class FavoriteClickUseCase(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(product: Product) = productsRepository.updateProduct(product)
}