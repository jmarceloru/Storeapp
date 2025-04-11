package com.example.data.di

import com.example.data.ProductsRepositoryService
import com.example.domain.repository.ProductsRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    factoryOf(::ProductsRepositoryService) bind ProductsRepository::class
}