package com.example.storeapp.ui.screens.categoriesdetail


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable


@OptIn(ExperimentalMaterial3Api::class)
class CategoryDetailState(
    val scrollBehavior: TopAppBarScrollBehavior
) {
    @Composable
    fun ShowScreen(loadProducts: () -> Unit) {
        LaunchedEffect(key1 = Unit) {
            loadProducts()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberCategoryDetailState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
): CategoryDetailState {
    return remember {
        CategoryDetailState(scrollBehavior)
    }
}

/*
@OptIn(ExperimentalMaterial3Api::class)
val CategoryDetailStateSaver: Saver<CategoryDetailState, *> = Saver(
    save = { categDetailState ->
        categDetailState.scrollBehavior to categDetailState.ok.value
    },
    restore = {
        CategoryDetailState(
            scrollBehavior = it.first,
            ok = mutableStateOf(it.second)
        )
    }
)*/
