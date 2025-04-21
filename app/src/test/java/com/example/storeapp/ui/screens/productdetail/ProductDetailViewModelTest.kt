package com.example.storeapp.ui.screens.productdetail

import app.cash.turbine.test
import com.example.domain.models.Result
import com.example.domain.usecases.FavoriteClickUseCase
import com.example.domain.usecases.FetchProductByIdUseCase
import com.example.test.rule.CoroutinesTestRule
import com.example.test.unit.sampleProduct
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
class ProductDetailViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var fetchProductByIdUseCase: FetchProductByIdUseCase

    @Mock
    lateinit var favoriteClickUseCase: FavoriteClickUseCase

    private lateinit var viewModel: ProductDetailViewModel

    @Before
    fun setUp() {
        viewModel = ProductDetailViewModel(fetchProductByIdUseCase,favoriteClickUseCase)
    }

    @Test
    fun `Error is propagates when request fails`() = runTest{
        val error = RuntimeException("Error")
        whenever(fetchProductByIdUseCase(1)).thenThrow(error)

        viewModel.fetchProductById(1)

        viewModel.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Error(error), awaitItem())
        }
    }

    @Test
    fun `Products are request with success`() = runTest{
        val product = sampleProduct(1)
        whenever(fetchProductByIdUseCase(1)).thenReturn(product)

        viewModel.fetchProductById(1)

        viewModel.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(product), awaitItem())
        }
    }

    @Test
    fun `Favorite action calls the corresponding use case`()= runTest{
        val product = sampleProduct(1)
        whenever(fetchProductByIdUseCase(1)).thenReturn(product)

        viewModel.fetchProductById(1)

        viewModel.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(product), awaitItem())
            print(product)
            viewModel.onFavoriteClick()
            val updatedProduct = product.copy(favorite = true)
           assertEquals(Result.Success(updatedProduct),awaitItem())
        }
    }
}