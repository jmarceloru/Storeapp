package com.example.framework.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.framework.local.entities.CategoryEntity
import com.example.framework.local.entities.ProductEntity

@Database(entities = [CategoryEntity::class, ProductEntity::class], version = 1, exportSchema = false)
abstract class StoreDatabase : RoomDatabase(){
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
}