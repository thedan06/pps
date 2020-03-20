package com.dustanmbaga.pps

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dustanmbaga.pps.acaricide.Acaricide
import com.dustanmbaga.pps.acaricide.AcaricideDao


@Database(
    entities = [
        Acaricide::class
    ],
    version = 1,
    exportSchema = false
)
abstract class PssDatabase: RoomDatabase() {

    abstract fun acaricideDao(): AcaricideDao

    companion object {

        /*private val acaricidesFun = Acaricide::populateData
        val acaricides = acaricidesFun.call()*/

        /*val initialAcaricides =  arrayOf(
        Acaricide(11, "Taktic 12.5EC", "Amitraz 125g/l", "AC/0018", "Intervet South Africa (Pty)", "Control of Mange, mites, ticks and lice on cattle.", "Full Registration"),

        Acaricide(2, "Tixfix 12.5%EC", "Amitraz 125g/l", "AC/0019", "Rotam Limited-Kenya", "Control of Ticks on cattle.", "Full Registration"),

        Acaricide(3, "Toptix 12.5%EC", "Amitraz 125g/l", "AC/0020", "Alfasan International Holland", "Control of ticks & Ectoparasites on cattle", "Full Registration"),

        Acaricide(4, "Norotraz 12.5%", "Amitraz 125g/l", "AC/0022", "Norbrook Laboratories-Kenya", "Control of Ticks on cattle.", "Full Registration")
        )*/

        @Volatile
        var database: PssDatabase? = null

        fun getInstance(context: Context): PssDatabase? {
            if (database == null) {
                synchronized(PssDatabase::class.java) {
                    if (database == null) {

                        database = Room.databaseBuilder(
                            context.applicationContext,
                            PssDatabase::class.java,
                            "pss_database"
                        )
                        /*.addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                Executors.newSingleThreadScheduledExecutor()
                                    .execute {

                                        initialAcaricides.iterator().forEach {
                                            getInstance(context)?.acaricideDao()?.insertAcaricide(it)
                                        }

                                    }
                            }
                        })*/
                        .build()

                    }
                }
            }

            return database
        }
    }
}