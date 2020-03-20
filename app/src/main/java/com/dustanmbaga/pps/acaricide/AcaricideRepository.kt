package com.dustanmbaga.pps.acaricide

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.dustanmbaga.pps.PssDatabase


class AcaricideRepository(application: Application) {
    private var acaricideDao: AcaricideDao?
    private val allAcaricides: LiveData<List<Acaricide>>?

    init {
        val database = PssDatabase.getInstance(application)

        acaricideDao = database?.acaricideDao()
        allAcaricides = acaricideDao?.getAllAcaricides()
    }

    fun insertAcaricide(acaricide: Acaricide) {
        InsertAsyncTask(acaricideDao!!).execute(acaricide)
    }

    fun deleteAcaricide(acaricide: Acaricide) {
        DeleteAsyncTask(acaricideDao!!).execute(acaricide)
    }

    fun deleteAllAcaricide() {
        DeleteAllAsyncTask(acaricideDao!!).execute()
    }

    fun getAllAcaricides(): LiveData<List<Acaricide>> {
        return allAcaricides!!
    }

    fun getAcaricideByTradeName(tradeName: String): Acaricide? {
        val acaricidesList = allAcaricides?.value?.toList()

        acaricidesList?.iterator()?.forEach {
            if (it.tradeName == tradeName) {
                return it
            }
        }

        return null
    }

    fun getAcaricidesByCommonName(commonName: String): LiveData<Acaricide?>? {
        return acaricideDao?.getAcaricideByCommonName(commonName)
    }

    fun getAcaricidesByRegCategory(regCategory: String): LiveData<List<Acaricide>>? {
        /*val acaricidesList = allAcaricides?.value?.toList()

        return acaricidesList?.filter {
            it -> it.regCategory == regCategory
        }*/

        return acaricideDao?.getAcaricidesByRegCategory(regCategory)
    }

    private class InsertAsyncTask(private val acaricideDao: AcaricideDao): AsyncTask<Acaricide, Void, Void>() {
        override fun doInBackground(vararg params: Acaricide): Void? {
            acaricideDao.insertAcaricide(params[0])

            return null
        }
    }

    private class DeleteAsyncTask(private val acaricideDao: AcaricideDao): AsyncTask<Acaricide, Void, Void>() {
        override fun doInBackground(vararg params: Acaricide): Void? {
            acaricideDao.deleteAcaricide(params[0])

            return null
        }
    }

    private class DeleteAllAsyncTask(private val acaricideDao: AcaricideDao): AsyncTask<Acaricide, Void, Void>() {
        override fun doInBackground(vararg params: Acaricide): Void? {
            acaricideDao.deleteAllAcaricides()

            return null
        }
    }
}