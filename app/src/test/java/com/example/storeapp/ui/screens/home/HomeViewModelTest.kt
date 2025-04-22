package com.example.storeapp.ui.screens.home

import app.cash.turbine.test
import com.example.domain.models.Result
import com.example.domain.usecases.FetchCategoriesUseCase
import com.example.test.rule.CoroutinesTestRule
import kotlinx.coroutines.flow.flow
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
class HomeViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var fetchCategoriesUseCase: FetchCategoriesUseCase

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel(fetchCategoriesUseCase)
    }

    @Test
    fun `Error is propagated when request fails`() = runTest{
        val error = RuntimeException("Error")
        whenever(fetchCategoriesUseCase.invoke()).thenReturn(flow { throw error})

        viewModel = HomeViewModel(fetchCategoriesUseCase)

        viewModel.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Error(error), awaitItem())
        }
    }
}