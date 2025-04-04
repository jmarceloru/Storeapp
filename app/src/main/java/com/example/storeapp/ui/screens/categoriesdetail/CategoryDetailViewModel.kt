package com.example.storeapp.ui.screens.categoriesdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapp.Result
import com.example.storeapp.domain.models.Product
import com.example.storeapp.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CategoryDetailViewModel(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    private var _state: MutableStateFlow<Result<List<Product>>> =
        MutableStateFlow(Result.Loading)
    val state: StateFlow<Result<List<Product>>> get() = _state.asStateFlow()

    fun loadProducts(category: String) {
        viewModelScope.launch {
            try {
                productsRepository.fetchProductsByCategory(category).collect {
                    _state.value = Result.Success(it)
                }
            }catch (ex: Exception){
                _state.value = Result.Error(ex)
            }
        }
    }
}