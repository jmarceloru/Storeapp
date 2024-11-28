package com.example.storeapp.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.storeapp.data.ProductsServiceRepository
import com.example.storeapp.data.remote.ProductRemoteDataSource
import com.example.storeapp.ui.screens.categoriesdetail.CategoriesDetailScreen
import com.example.storeapp.ui.screens.categoriesdetail.CategoryDetailViewModel
import com.example.storeapp.ui.screens.home.HomeScreen
import com.example.storeapp.ui.screens.home.HomeViewModel
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class CategoryDetail(val category:String)

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            val productRemoteDataService = ProductRemoteDataSource()
            val productsRepository = ProductsServiceRepository(productRemoteDataService)
            val homeViewModel = HomeViewModel(productsRepository)
            HomeScreen(viewModel {
                homeViewModel
            }){
                navController.navigate(route = CategoryDetail(it))
            }
        }
        composable<CategoryDetail> {  backStackEntry ->
            val category = backStackEntry.toRoute<CategoryDetail>()
            val productRemoteDataService = ProductRemoteDataSource()
            val productsRepository = ProductsServiceRepository(productRemoteDataService)
            val vm = CategoryDetailViewModel(productsRepository)
            CategoriesDetailScreen(category.category,
                viewModel {
                    vm
                }) { navController.popBackStack() }
        }
    }
}