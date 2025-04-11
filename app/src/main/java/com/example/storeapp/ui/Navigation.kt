package com.example.storeapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.storeapp.ui.screens.categoriesdetail.CategoriesDetailScreen
import com.example.storeapp.ui.screens.home.HomeScreen
import com.example.storeapp.ui.screens.productdetail.ProductDetailScreen
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object Home

@Serializable
data class CategoryDetail(val category: String)

@Serializable
data class ProductDetail(val id: Int)

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeScreen(koinViewModel()) {
                navController.navigate(route = CategoryDetail(it))
            }
        }
        composable<CategoryDetail> { backStackEntry ->
            val category = backStackEntry.toRoute<CategoryDetail>()
            CategoriesDetailScreen(category.category,
                koinViewModel(),
                { navController.popBackStack() },
                { navController.navigate(route = ProductDetail(it.id)) })
        }
        composable<ProductDetail> { backStackEntry ->
            val product = backStackEntry.toRoute<ProductDetail>()
            ProductDetailScreen(viewModel = koinViewModel(), idProduct = product.id) {
                navController.popBackStack()
            }
        }
    }
}