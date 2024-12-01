package com.example.storeapp.ui.screens.productdetail

import com.example.storeapp.domain.models.Product

data class ProductDetailUiState(
    val loading: Boolean = false,
    val product: Product = Product()
)