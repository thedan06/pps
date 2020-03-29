package com.dustanmbaga.pps.acaricide

import android.util.Log
import androidx.lifecycle.MutableLiveData


class AcaricideRepository(private val network: PssNetwork, private val acaricideDao: AcaricideDao) {

    val showProgress = MutableLiveData<Boolean>()

    // Observing DB via Repository
    var acaricideList = acaricideDao.getAcaricidesLiveData()

    fun changeState() {
        showProgress.value = !(showProgress.value != null && showProgress.value!!)
    }

    // Fetching Acaricides from API and insert/update them into the Database.
    suspend fun refreshAcaricides() {
        try {
            // Calling the API
            val acaricidesList = network.getAcaricides("contents").map {
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

            // Inserting fetched Acaricides into the Database
            //acaricideDao.insertAcaricides(acaricidesList)
            acaricidesList.forEach {
                acaricideDao.insertAcaricide(it)
            }

        } catch (error: Throwable) {
            Log.d("AcaricideRepository", "Unable to refresh Acaricides")
            throw AcaricideRefreshError("Unable to refresh Acaricides", error)
        }
    }

    suspend fun getAcaricideFromApi() = network.getAcaricides("contents").map {
        Acaricide(
            it.id.toInt(),
            it.trade_name,
            it.common_name,
            it.reg_number,
            it.registrant,
            it.usage,
            it.reg_category
        )
    }.toList()

}

class AcaricideRefreshError(message: String, cause: Throwable) : Throwable(message, cause)