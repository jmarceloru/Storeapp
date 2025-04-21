package com.example.storeapp.ui.screens.home

import app.cash.turbine.test
import com.example.domain.models.Result
import com.example.domain.usecases.FetchCategoriesUseCase
import com.example.test.rule.CoroutinesTestRule
import com.example.test.unit.sampleCategories
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
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

 /*   @Test
    fun `Error is propagated when request fails`(): Unit = runTest{
        val error = RuntimeException("Error")
        whenever(fetchCategoriesUseCase()).thenThrow(error)

        viewModel.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Error(error), awaitItem())
        }
    }*/
}