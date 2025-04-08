package com.example.storeapp.ui.screens.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapp.Result
import com.example.domain.models.Product
import com.example.domain.usecases.FavoriteClickUseCase
import com.example.domain.usecases.FetchProductByIdUseCase
import com.example.storeapp.ifSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val fetchProductByIdUseCase: FetchProductByIdUseCase,
    private val favoriteClickUseCase: FavoriteClickUseCase
) : ViewModel() {

    private var _state: MutableStateFlow<Result<Product>> = MutableStateFlow(Result.Loading)
    val state: StateFlow<Result<Product>> get() = _state.asStateFlow()

    fun fetchProductById(idProduct: Int) {
        viewModelScope.launch {
            try {
                _state.value = Result.Success(fetchProductByIdUseCase(idProduct))
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
                        favoriteClickUseCase(p)
                    }
                }
            }
        }
    }
}