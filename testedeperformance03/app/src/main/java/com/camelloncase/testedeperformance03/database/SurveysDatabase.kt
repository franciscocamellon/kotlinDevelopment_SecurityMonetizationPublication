package com.camelloncase.testedeperformance03.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Survey::class, Surveyor::class], version = 4, exportSchema = false)
abstract class SurveysDatabase : RoomDatabase() {

    abstract fun getSurveysDao(): SurveysDao

    companion object {

        @Volatile
        private var INSTANCE: SurveysDatabase? = null

        fun getDatabase(context: Context): SurveysDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SurveysDatabase::class.java,
                    "survey_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }
}
