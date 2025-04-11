package com.example.framework.di

import android.content.Context
import androidx.room.Room
import com.example.data.datasources.ProductLocalDataService
import com.example.data.datasources.ProductRemoteDataService
import com.example.framework.local.ProductLocalDataSource
import com.example.framework.local.database.StoreDatabase
import com.example.framework.remote.ProductRemoteDataSource
import com.example.framework.remote.retrofit.ProductClient
import org.koin.dsl.module

val frameWorkModule = module(moduleDeclaration = {
    single {
        Room.databaseBuilder(
            get<Context>().applicationContext,
            StoreDatabase::class.java,
            "store-db"
        )
            .build()
    }
    factory { ProductClient.instance }
    factory { get<StoreDatabase>().categoryDao() }
    factory { get<StoreDatabase>().productDao() }
    factory<ProductLocalDataService> { ProductLocalDataSource(get(), get()) }
    factory<ProductRemoteDataService> { ProductRemoteDataSource(get()) }
})