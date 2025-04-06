package com.example.storeapp.domain.usecases

import com.example.storeapp.domain.models.Category
import com.example.storeapp.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

class FetchCategoriesUseCase(
    private val productsRepository: ProductsRepository
) {
    operator fun invoke(): Flow<List<Category>> = productsRepository.fetchCategories()
}