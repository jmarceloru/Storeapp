package com.example.domain.usecases

import com.example.domain.models.Category
import com.example.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

class FetchCategoriesUseCase(
    private val productsRepository: ProductsRepository
) {
    operator fun invoke(): Flow<List<Category>> = productsRepository.fetchCategories()
}