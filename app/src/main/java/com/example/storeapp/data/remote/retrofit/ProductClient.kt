package com.example.storeapp.data.remote.retrofit

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object ProductClient{

    private const val FORMAT = "application/json"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    val instance: ProductService = Retrofit
        .Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(json.asConverterFactory(FORMAT.toMediaType()))
        .build()
        .create(ProductService::class.java)
}