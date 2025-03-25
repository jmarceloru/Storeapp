package com.example.storeapp.ui.screens.productdetail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@OptIn(ExperimentalMaterial3Api::class)
class ProductDetailState(
    val scrollBehavior: TopAppBarScrollBehavior
) {

    @Composable
    fun ShowScreen(loadData: () -> Unit) {
        LaunchedEffect(key1 = Unit) {
            loadData()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberProductDetailState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
): ProductDetailState {
    return remember {
        ProductDetailState(scrollBehavior)
    }
}