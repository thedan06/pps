package com.dustanmbaga.pps.acaricide

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//class AcaricideRepository(val application: Application, val acaricideDao: AcaricideDao, val network: PssNetwork) {
class AcaricideRepository(val network: PssNetwork, val acaricideDao: AcaricideDao) {

    val showProgress = MutableLiveData<Boolean>()
    var acaricideList = MutableLiveData<List<AcaricideNetworkResponse>>()

    fun changeState() {
        showProgress.value = !(showProgress.value != null && showProgress.value!!)
    }


    /*fun getAcaricideFromApi() {

        showProgress.value = true

        network.getAcaricide("contents").enqueue(object : Callback<List<AcaricideNetworkResponse>> {
            override fun onFailure(call: Call<List<AcaricideNetworkResponse>>, t: Throwable) {
                showProgress.value = false

//                Toast.makeText(application, "Error when calling the API", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<AcaricideNetworkResponse>>,
                response: Response<List<AcaricideNetworkResponse>>
            ) {
                Log.d("AcaricideRepository", "Response: ${Gson().toJson(response.body())}")

                acaricideList.value = response.body()
                showProgress.value = false
            }

        })
    }*/

    // ------------
    //val acaricideList: LiveData<List<Acaricide>> = acaricideDao.getAcaricidesLiveData()

    // Fetching Acaricides from API and insert/update them into the Database.
    /*suspend fun refreshAcaricides() {
        try {
            // Calling the API
            val acaricidesList = network.getAcaricides("contents")

            // Inserting fetched Acaricides into the Database
            acaricideDao.insertAcaricides(acaricidesList)

            *//*if (acaricidesList != null) {
                // Inserting fetched Acaricides into the Database
                //acaricideDao.insertAcaricides(acaricidesList)
            } else {
                Log.d("AcaricideRepository", "No Acaricide have been returned from the API hence nothing has been updated in the database.")
            }*//*

        } catch (error: Throwable) {
            Log.d("AcaricideRepository", "Unable to refresh Acaricides")
            throw AcaricideRefreshError("Unable to refresh Acaricides", error)
        }
    }*/

    //var client = getNetworkService()
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