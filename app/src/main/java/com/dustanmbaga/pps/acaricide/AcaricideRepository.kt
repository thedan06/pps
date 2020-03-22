package com.dustanmbaga.pps.acaricide

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AcaricideRepository(val application: Application) {

    val showProgress = MutableLiveData<Boolean>()
    val acaricideList = MutableLiveData<List<AcaricideNetworkResponse>>()

    fun changeState() {
        showProgress.value = !(showProgress.value != null && showProgress.value!!)
    }

    fun getAcaricideFromApi() {
        showProgress.value = true

        //Call API
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(AcaricideNetwork::class.java)

        service.getAcaricide("contents").enqueue(object : Callback<List<AcaricideNetworkResponse>>{
            override fun onFailure(call: Call<List<AcaricideNetworkResponse>>, t: Throwable) {
                showProgress.value = false

                Toast.makeText(application, "Error when calling the API", Toast.LENGTH_LONG).show()
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
    }

}