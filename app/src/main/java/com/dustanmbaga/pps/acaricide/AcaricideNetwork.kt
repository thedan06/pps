package com.dustanmbaga.pps.acaricide

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://my-json-server.typicode.com/thedan06/test_api/"
//"https://github.com/thedan06/test_api/blob/master/db.json"
interface AcaricideNetwork {

    @GET("{contents}")
    fun getAcaricide(@Path("contents") contents: String): Call<List<AcaricideNetworkResponse>>
}