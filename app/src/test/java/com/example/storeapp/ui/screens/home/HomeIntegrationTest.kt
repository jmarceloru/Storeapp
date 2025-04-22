package com.example.storeapp.ui.screens.home

import app.cash.turbine.test
import com.example.domain.models.Result
import com.example.domain.usecases.FetchCategoriesUseCase
import com.example.storeapp.ui.screens.home.models.CategoryModel
import com.example.test.data.buildProductRepositoryService
import com.example.test.rule.CoroutinesTestRule
import com.example.test.unit.sampleCategoriesData
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class HomeIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `Data is loaded from server when local data source is empty`() = runTest {
        val remoteDataCategories = sampleCategoriesData("jewerly", "electronics")
        val vm = HomeViewModel(
            FetchCategoriesUseCase(
                buildProductRepositoryService(
                    remoteDataCategories = remoteDataCategories,
                )
            )
        )

        vm.state.test {
            assertEquals(Result.Loading,awaitItem())
            assertEquals(Result.Success(emptyList()),awaitItem())
            val categories: List<CategoryModel> = remoteDataCategories.map { cat ->
                CategoryModel(
                    cat.title,
                    vm.getImage(cat.title)
                )
            }
            assertEquals(Result.Success(categories),awaitItem())
        }
    }

    @Test
    fun `Data is loaded from local data source when is available`() = runTest {
        val localDataCategories = sampleCategoriesData("jewerly", "electronics")
        val vm = HomeViewModel(
            FetchCategoriesUseCase(
                buildProductRepositoryService(
                    localDataCategories = localDataCategories
                )
            )
        )

        vm.state.test {
            assertEquals(Result.Loading,awaitItem())
            val categories: List<CategoryModel> = localDataCategories.map { cat ->
                CategoryModel(
                    cat.title,
                    vm.getImage(cat.title)
                )
            }
            assertEquals(Result.Success(categories),awaitItem())
        }
    }
}