package com.dustanmbaga.pps.acaricide

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://my-json-server.typicode.com/thedan06/test_api/"
//"https://github.com/thedan06/test_api/blob/master/db.json"
/*interface AcaricideNetwork {

    @GET("{contents}")
    fun getAcaricide(@Path("contents") contents: String): Call<List<AcaricideNetworkResponse>>

}*/

//----------------
val service: PssNetwork by lazy {
    val okHttpClient = OkHttpClient.Builder()
        //.addInterceptor(SkipNetworkInterceptor())
//        .readTimeout(180, TimeUnit.SECONDS)
//        .connectTimeout(180, TimeUnit.SECONDS)
//        .writeTimeout(180, TimeUnit.SECONDS)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    retrofit.create(PssNetwork::class.java)
}

/*val service: PssNetwork by lazy {
    Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
        .create(PssNetwork::class.java)
}*/

fun getNetworkService() = service

interface PssNetwork {

    @GET("{contents}")
    fun getAcaricide(@Path("contents") contents: String): Call<List<AcaricideNetworkResponse>>

    @GET("{contents}")
    /*fun getAcaricides(@Path("contents") contents: String): Call<List<AcaricideNetworkResponse>>*/
    suspend fun getAcaricides(@Path("contents") contents: String): List<AcaricideNetworkResponse>

    /*suspend fun getAcaricidesFromApi(): Array<Acaricide>? {

        var result: List<AcaricideNetworkResponse>? = null

        service.getAcaricides("contents").enqueue(object : Callback<List<AcaricideNetworkResponse>> {

            override fun onFailure(call: Call<List<AcaricideNetworkResponse>>, t: Throwable) {
                Log.d("PssNetwork", "Error when calling the API")
            }

            override fun onResponse(
                call: Call<List<AcaricideNetworkResponse>>,
                response: Response<List<AcaricideNetworkResponse>>
            ) {
                Log.d("PssNetwork", "Successfully called the API")
                Log.d("PssNetwork", "Response: ${Gson().toJson(response.body())}")

                result = response.body()
            }

        })

        // Remove after testing
        result?.forEach {
            Log.d("PssNetwork", "Common name: ${it.common_name}")
        }

        // Transforming List of AcaricideNetworkResponse to Array of Acaricide
        return if (result != null) {
            result!!.map {
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
        } else {
            null
        }
    }*/

    /*//---------
    fun getData (): MutableLiveData<List<AcaricideNetworkResponse>> {
        getAcaricideFromApi()

        return this.acaricideList
    }

    val acaricideList: MutableLiveData<List<AcaricideNetworkResponse>>

    fun getAcaricideFromApi() {

        service.getAcaricide("contents").enqueue(object : Callback<List<AcaricideNetworkResponse>> {
            override fun onFailure(call: Call<List<AcaricideNetworkResponse>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<AcaricideNetworkResponse>>,
                response: Response<List<AcaricideNetworkResponse>>
            ) {
                Log.d("AcaricideRepository", "Response: ${Gson().toJson(response.body())}")

                acaricideList.value = response.body()
            }
        })
    }*/
}
