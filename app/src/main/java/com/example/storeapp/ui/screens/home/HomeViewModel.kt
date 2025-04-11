package com.example.storeapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Result
import com.example.domain.models.Category
import com.example.domain.usecases.FetchCategoriesUseCase
import com.example.storeapp.R
import com.example.storeapp.ui.screens.home.models.Categories
import com.example.storeapp.ui.screens.home.models.CategoryModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
   fetchCategoriesUseCase: FetchCategoriesUseCase
) : ViewModel(){

  //  private var _state = MutableStateFlow(HomeUiState())
    //val state: StateFlow<HomeUiState> get() = _state.asStateFlow()

    val state: StateFlow<Result<List<CategoryModel>>> = fetchCategoriesUseCase()
        .map<List<Category>, Result<List<CategoryModel>>> { Result.Success(it.map { cat->
            CategoryModel(
                cat.title,
                getImage(cat.title)
            )
        }) }
        .catch {emit(Result.Error(it)) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Result.Loading
        )

    private fun getImage(title: String): Int =
        when (title) {
            Categories.Electronics.title -> R.drawable.electronics
            Categories.Jewerly.title -> R.drawable.jewelery
            Categories.MenClothing.title -> R.drawable.menclothes
            Categories.WomenClothing.title -> R.drawable.womenclotehs
            else -> R.drawable.notfound
        }

    /*init {
        viewModelScope.launch {
            _state.value = HomeUiState(loading = true)
            productsRepository.fetchCategories().collect{
                _state.value = HomeUiState(false,it)
            }
        }
    }*/
}