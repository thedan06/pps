package com.dustanmbaga.pps

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dustanmbaga.pps.acaricide.Acaricide
import com.dustanmbaga.pps.acaricide.AcaricideDao


/**
 * PssDatabase provides a reference to the dao to repositories
 */
@Database(
    entities = [Acaricide::class],
    version = 1,
    exportSchema = false
)
abstract class PssDatabase : RoomDatabase() {
    abstract val acaricideDao: AcaricideDao
}

private lateinit var INSTANCE: PssDatabase

/**
 * Instantiate a database from a context.
 */
fun getDatabase(context: Context): PssDatabase {
    synchronized(PssDatabase::class) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room
                .databaseBuilder(
                    context.applicationContext,
                    PssDatabase::class.java,
                    "pss_db"
                )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}