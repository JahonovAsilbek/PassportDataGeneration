package com.tuit_21019.passportdatageneration.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tuit_21019.passportdatageneration.dao.CitizenDao
import com.tuit_21019.passportdatageneration.entities.Citizen

@Database(entities = [Citizen::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun citizenDao(): CitizenDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun init(context: Context){
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "citizen_database"
                )
                    .allowMainThreadQueries()
                    .build()
            }
        }
    }

    object get{
        fun getDatabase():AppDatabase{
            return instance!!
        }
    }
}