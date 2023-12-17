package com.example.areasrecreativasapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [Area::class, Sos::class], version = 1, exportSchema = true)
abstract class AreaDatabase : RoomDatabase() {

    abstract fun areaDao(): AreaDao
    abstract fun sosDao(): SosDao

    companion object {
        @Volatile
        private var Instance: AreaDatabase? = null

        fun getDatabase(context: Context): AreaDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AreaDatabase::class.java, "local_database")
                    .createFromAsset("database/areas_database.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}