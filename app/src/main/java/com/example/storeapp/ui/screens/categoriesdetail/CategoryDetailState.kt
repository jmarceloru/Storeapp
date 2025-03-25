package com.example.storeapp.ui.screens.categoriesdetail


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable


@OptIn(ExperimentalMaterial3Api::class)
class CategoryDetailState(
    val scrollBehavior: TopAppBarScrollBehavior,
    val ok: MutableState<Boolean>
)  {

    @Composable
    fun ShowScreen(ok: MutableState<Boolean>, loadProducts: () -> Unit) {
        LaunchedEffect(ok) {
            if (!ok.value) {
                ok.value = true
                loadProducts()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RememberCategoryDetailState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    ok: MutableState<Boolean> = mutableStateOf(false)
): CategoryDetailState {
    return rememberSaveable(saver = CategoryDetailStateSaver) {
        CategoryDetailState(scrollBehavior, ok)
    }
}

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
)