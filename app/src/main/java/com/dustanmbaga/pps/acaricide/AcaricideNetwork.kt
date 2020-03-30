package com.dustanmbaga.pps.acaricide

import android.util.Log
import com.dustanmbaga.pps.util.SkipNetworkInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://my-json-server.typicode.com/thedan06/test_api/"
//"https://github.com/thedan06/test_api/blob/master/db.json"

val service: PssNetwork by lazy {
    val okHttpClient = OkHttpClient.Builder()
//        .addInterceptor(SkipNetworkInterceptor())
        .readTimeout(180, TimeUnit.SECONDS)
        .connectTimeout(180, TimeUnit.SECONDS)
        .writeTimeout(180, TimeUnit.SECONDS)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    retrofit.create(PssNetwork::class.java)
}

fun getNetworkService() = service

interface PssNetwork {

    @GET("{contents}")
    suspend fun getAcaricides(@Path("contents") contents: String): List<AcaricideNetworkResponse>

}
