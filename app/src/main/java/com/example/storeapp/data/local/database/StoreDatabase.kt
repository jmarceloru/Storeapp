package com.example.storeapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.storeapp.data.local.entities.Category
import com.example.storeapp.data.local.entities.Product

@Database(entities = [Category::class, Product::class], version = 1, exportSchema = false)
abstract class StoreDatabase : RoomDatabase(){
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
}