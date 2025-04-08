package com.example.storeapp.ui.screens.home

import com.example.domain.models.Category

data class HomeUiState(
    val loading: Boolean = false,
    val categories: List<Category> = emptyList()
)
