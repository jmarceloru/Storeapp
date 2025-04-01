package com.example.storeapp.ui.screens.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.EMPTY_STRING_ARRAY
import com.example.storeapp.Result
import com.example.storeapp.domain.models.Product
import com.example.storeapp.domain.repository.ProductsRepository
import com.example.storeapp.ifSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    private var _state: MutableStateFlow<Result<Product>> = MutableStateFlow(Result.Loading)
    val state: StateFlow<Result<Product>> get() = _state.asStateFlow()

    fun fetchProduct(idProduct: Int) {
        viewModelScope.launch {
            try {
                _state.value = Result.Success(productsRepository.fetchProductById(idProduct))
            } catch (ex: Exception) {
                _state.value = Result.Error(ex)
            }
        }
    }

    fun onFavoriteClick() {
        _state.value.ifSuccess { product ->
            viewModelScope.launch {
                _state.update {
                    val p = product.copy(favorite = !product.favorite)
                    Result.Success(p).also {
                        productsRepository.updateProduct(p)
                    }
                }
            }
        }
    }
}