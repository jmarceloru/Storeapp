package com.example.framework.local

import android.content.Context
import androidx.room.Room
import com.example.framework.local.database.StoreDatabase

class CreateDatabaseService(
    private val context: Context
) {

    fun createDatabase(): StoreDatabase =
         Room.databaseBuilder(context, StoreDatabase::class.java, "store-db")
            .build()
}