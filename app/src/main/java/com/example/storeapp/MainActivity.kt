package com.example.storeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.storeapp.data.ProductsServiceRepository
import com.example.storeapp.data.remote.ProductRemoteDataSource
import com.example.storeapp.ui.Navigation
import com.example.storeapp.ui.screens.home.HomeScreen
import com.example.storeapp.ui.screens.home.HomeViewModel
import com.example.storeapp.ui.theme.StoreAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StoreAppTheme {
                Navigation()
            }
        }
    }
}

