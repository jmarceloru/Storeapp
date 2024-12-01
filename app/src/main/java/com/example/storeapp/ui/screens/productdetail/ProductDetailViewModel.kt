package com.example.storeapp.ui.screens.productdetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapp.domain.repository.ProductsRepository
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val productsRepository: ProductsRepository
): ViewModel() {

    var state by mutableStateOf(ProductDetailUiState())
        private set

    fun fetchProduct(idProduct: Int){
        viewModelScope.launch {
            state = ProductDetailUiState(loading = true)
            state = ProductDetailUiState(loading = false, productsRepository.fetchProductById(idProduct))
        }
    }
}