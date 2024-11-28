package com.example.storeapp.ui.screens.categoriesdetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapp.domain.repository.ProductsRepository
import kotlinx.coroutines.launch


class CategoryDetailViewModel(
    private val productsRepository: ProductsRepository
) : ViewModel(){

    var state by mutableStateOf(CategoryDetailUiState())
        private set

    fun loadProducts(category: String){
        viewModelScope.launch {
            state =  CategoryDetailUiState(true)
            state = CategoryDetailUiState(false,productsRepository.fetchProductsByCategory(category))
        }
    }
}