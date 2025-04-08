package com.example.storeapp.ui.screens.home.models

sealed class Categories(val title:String){
    data object Electronics: Categories("electronics")
    data object Jewerly: Categories("jewelery")
    data object MenClothing: Categories("men's clothing")
    data object WomenClothing: Categories("women's clothing")
}