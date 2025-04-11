package com.example.storeapp.di

import com.example.storeapp.ui.screens.categoriesdetail.CategoryDetailViewModel
import com.example.storeapp.ui.screens.home.HomeViewModel
import com.example.storeapp.ui.screens.productdetail.ProductDetailViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::CategoryDetailViewModel)
    viewModelOf(::ProductDetailViewModel)
}