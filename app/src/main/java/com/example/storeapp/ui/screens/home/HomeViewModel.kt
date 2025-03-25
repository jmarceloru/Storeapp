package com.example.storeapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapp.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val productsRepository : ProductsRepository
) : ViewModel(){

    private var _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = HomeUiState(loading = true)
            _state.value = HomeUiState(loading = false, categories = productsRepository.fetchCategories())
        }
    }
}