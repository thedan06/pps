package com.dustanmbaga.pps.acaricide

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*


class AcaricideRepository(private val network: PssNetwork, private val acaricideDao: AcaricideDao) {

    val showProgress = MutableLiveData<Boolean>()

    // Observing DB via Repository
    //var acaricideList = acaricideDao.getAcaricidesLiveData()
    fun getAcaricideFromDB(): LiveData<List<Acaricide>> {
        showProgress.value = false

        val acaricides = acaricideDao.getAcaricidesLiveData()

        Log.i("AcaricideRepository", "Fetched from DB these acaricides: $acaricides")

        return acaricides
    }

    //-------------------
    fun searchAcaricideFromDB(searchText: String): LiveData<List<Acaricide>> {
        //showProgress.value = false
        val acaricides = acaricideDao.searchAcaricidesLiveData(searchText.toLowerCase(Locale.ROOT))

        Log.i("AcaricideRepository", "Searched from DB these acaricides: $acaricides")

        return acaricides
    }
    //-------------------

    /*fun changeState() {
        showProgress.value = !(showProgress.value != null && showProgress.value!!)
    }*/

    // Fetching Acaricides from API and insert/update them into the Database.
    suspend fun refreshAcaricides() {
        try {
            // Calling the API
            val acaricidesList = getAcaricideFromApi()

            // Inserting fetched Acaricides into the Database
            //acaricideDao.insertAcaricides(acaricidesList)
            acaricidesList.forEach {
                Log.i("AcaricideRepository", "Inserting into DB this acaricide: $it")
                acaricideDao.insertAcaricide(it)
            }

        } catch (error: Throwable) {
            Log.d("AcaricideRepository", "Unable to refresh Acaricides: Error ${error.message} + ${error.localizedMessage}")
            throw AcaricideRefreshError("Unable to refresh Acaricides", error)
        }
    }

    private suspend fun getAcaricideFromApi() = network.getAcaricides("contents").map {

        Log.i("AcaricideRepository", "Fetching data from an API...")

        Acaricide(
            it.id.toInt(),
            it.trade_name,
            it.common_name,
            it.reg_number,
            it.registrant,
            it.usage,
            it.reg_category
        )
    }.toTypedArray()

}

class AcaricideRefreshError(message: String, cause: Throwable) : Throwable(message, cause)