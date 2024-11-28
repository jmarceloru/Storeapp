package com.example.storeapp.ui.screens.categoriesdetail

import com.example.storeapp.domain.models.Product

data class CategoryDetailUiState(
    val loading: Boolean = false,
    val productList: List<Product> = emptyList()
)
