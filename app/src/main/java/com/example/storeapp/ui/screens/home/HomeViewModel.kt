package com.example.storeapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapp.Result
import com.example.storeapp.domain.models.Category
import com.example.storeapp.domain.repository.ProductsRepository
import com.example.storeapp.ui.Home
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch

class HomeViewModel(
   productsRepository : ProductsRepository
) : ViewModel(){

  //  private var _state = MutableStateFlow(HomeUiState())
    //val state: StateFlow<HomeUiState> get() = _state.asStateFlow()

    val state: StateFlow<Result<List<Category>>> = productsRepository.fetchCategories()
        .map<List<Category>,Result<List<Category>> > { Result.Success(it) }
        .catch {emit(Result.Error(it)) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Result.Loading
        )

    /*init {
        viewModelScope.launch {
            _state.value = HomeUiState(loading = true)
            productsRepository.fetchCategories().collect{
                _state.value = HomeUiState(false,it)
            }
        }
    }*/
}