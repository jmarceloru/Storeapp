package com.example.storeapp

import android.app.Application
import com.example.framework.local.CreateDatabaseService
import com.example.framework.local.database.StoreDatabase

class StoreApp : Application() {

    private val createDatabase = CreateDatabaseService(this)
    lateinit var db: StoreDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        db = createDatabase.createDatabase()
    }
}