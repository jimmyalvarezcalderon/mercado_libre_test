package com.project.mercado_libre_test.services.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [], version = 1)
abstract class ProjectDatabase : RoomDatabase() {

    companion object {
        fun getInstance(context: Context): ProjectDatabase {
            return Room.databaseBuilder(
                context,
                ProjectDatabase::class.java, "project-database"
            ).build()

        }
    }
}