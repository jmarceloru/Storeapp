package com.example.domain.di

import com.example.domain.usecases.FavoriteClickUseCase
import com.example.domain.usecases.FetchCategoriesUseCase
import com.example.domain.usecases.FetchProductByIdUseCase
import com.example.domain.usecases.FetchProductsByCategoryUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::FetchCategoriesUseCase)
    factoryOf(::FetchProductsByCategoryUseCase)
    factoryOf(::FetchProductByIdUseCase)
    factoryOf(::FavoriteClickUseCase)
}