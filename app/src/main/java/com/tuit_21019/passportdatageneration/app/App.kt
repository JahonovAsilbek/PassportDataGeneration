package com.tuit_21019.passportdatageneration.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.tuit_21019.passportdatageneration.database.AppDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)
        // disable night mode settings
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}