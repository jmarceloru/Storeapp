package com.example.storeapp.ui.screens.categoriesdetail

import com.example.domain.models.Product

data class CategoryDetailUiState(
    val loading: Boolean = false,
    val productList: List<Product> = emptyList()
)
