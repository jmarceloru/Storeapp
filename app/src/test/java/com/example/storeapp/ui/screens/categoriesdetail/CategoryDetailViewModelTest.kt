package com.example.storeapp.ui.screens.categoriesdetail

import app.cash.turbine.test
import com.example.domain.models.Result
import com.example.domain.usecases.FetchProductsByCategoryUseCase
import com.example.test.rule.CoroutinesTestRule
import com.example.test.unit.sampleProducts
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class CategoryDetailViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var fetchProductsByCategoryUseCase: FetchProductsByCategoryUseCase

    private lateinit var viewModel: CategoryDetailViewModel

    @Before
    fun setUp() {
        viewModel = CategoryDetailViewModel(fetchProductsByCategoryUseCase)
    }

    @Test
    fun `Error is propagates when request fails`() = runTest {
        val error = RuntimeException("Error")
        whenever(fetchProductsByCategoryUseCase("jewerly")).thenThrow(error)

        viewModel.loadProducts("jewerly")

        viewModel.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Error(error), awaitItem())
        }
    }

    @Test
    fun `Products are request with success`() = runTest {
        val products = sampleProducts(1,2,3)
        whenever(fetchProductsByCategoryUseCase("jewerly")).thenReturn(flowOf(products))

        viewModel.loadProducts("jewerly")

        viewModel.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(products), awaitItem())
        }
    }
}