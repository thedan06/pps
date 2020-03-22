package com.dustanmbaga.pps.acaricide

import com.dustanmbaga.pps.util.SkipNetworkInterceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private val service: AcaricideNetwork by lazy {
    val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(SkipNetworkInterceptor())
            .build()

    val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    retrofit.create(AcaricideNetwork::class.java)
}

fun getNetworkService() = service

/**
 * Main network interface which will fetch a new welcome title for us
 */
interface AcaricideNetwork {
    @GET("next_title.json")
    suspend fun fetchNextTitle(): String

    @GET("acaricide.json")
    suspend fun fetchAcaricides(): Array<Acaricide>


    //----------------------
    /*@GET("lego/themes/")
    suspend fun getThemes(@Query("page") page: Int? = null,
                          @Query("page_size") pageSize: Int? = null,
                          @Query("ordering") order: String? = null): Response<ResultsResponse<LegoTheme>>

    @GET("lego/sets/")
    suspend fun getSets(@Query("page") page: Int? = null,
                        @Query("page_size") pageSize: Int? = null,
                        @Query("theme_id") themeId: Int? = null,
                        @Query("ordering") order: String? = null): Response<ResultsResponse<LegoSet>>

    @GET("lego/sets/{id}/")
    suspend fun getSet(@Path("id") id: String): Response<LegoSet>*/
}


