package com.example.storeapp.ui.screens.categoriesdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapp.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CategoryDetailViewModel(
    private val productsRepository: ProductsRepository
) : ViewModel(){

    private var _state = MutableStateFlow(CategoryDetailUiState())
    val state: StateFlow<CategoryDetailUiState> get() = _state.asStateFlow()

    fun loadProducts(category: String){
        viewModelScope.launch {
            _state.value =  CategoryDetailUiState(true)
            _state.value = CategoryDetailUiState(false,productsRepository.fetchProductsByCategory(category))
        }
    }
}