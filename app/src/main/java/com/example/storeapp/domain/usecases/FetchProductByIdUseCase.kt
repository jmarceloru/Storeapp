package com.example.storeapp.domain.usecases

import com.example.storeapp.domain.models.Product
import com.example.storeapp.domain.repository.ProductsRepository

class FetchProductByIdUseCase(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(id:Int):Product = productsRepository.fetchProductById(id)
}