package com.example.domain.usecases

import com.example.domain.models.Product
import com.example.domain.repository.ProductsRepository

class FetchProductByIdUseCase(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(id:Int): Product = productsRepository.fetchProductById(id)
}