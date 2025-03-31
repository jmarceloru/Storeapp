package com.example.storeapp.ui.screens.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapp.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    private var _state = MutableStateFlow(ProductDetailUiState())
    val state: StateFlow<ProductDetailUiState> get() = _state.asStateFlow()

    fun fetchProduct(idProduct: Int) {
        viewModelScope.launch {
            _state.value = ProductDetailUiState(loading = true)
            _state.value = ProductDetailUiState(
                loading = false,
                productsRepository.fetchProductById(idProduct)
            )
        }
    }

    fun onFavoriteClick() {
        viewModelScope.launch {
            _state.update {
                it.copy(product = it.product.copy(favorite = !it.product.favorite)).also { productDetailState->
                    productsRepository.updateProduct(productDetailState.product)
                }
            }
        }
    }
}