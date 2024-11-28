package com.example.storeapp.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapp.data.remote.ProductRemoteDataSource
import com.example.storeapp.domain.repository.ProductsRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val productsRepository : ProductsRepository
) : ViewModel(){

    var state by mutableStateOf(HomeUiState())
        private set

    init {
        viewModelScope.launch {
            state = HomeUiState(loading = true)
            state = HomeUiState(loading = false, categories = productsRepository.fetchCategories())
        }
    }
}