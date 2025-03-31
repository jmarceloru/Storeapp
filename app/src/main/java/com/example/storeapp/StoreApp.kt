package com.example.storeapp

import android.app.Application
import androidx.room.Room
import com.example.storeapp.data.local.database.StoreDatabase

class StoreApp : Application() {

    lateinit var db: StoreDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, StoreDatabase::class.java, "store-db")
            .build()
    }
}