package com.dustanmbaga.pps.acaricide

import androidx.lifecycle.LiveData
import androidx.room.*

/***
 * A database that will hold one Acaricide info.
 */
@Dao
interface AcaricideDao{

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAcaricide(acaricide: Acaricide)*/

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAcaricides(vararg acaricides: Array<Acaricide>)

    *//*@get:Query("SELECT * FROM acaricides ORDER BY common_name, reg_number, registrant ASC")
    val acaricidesLiveData: LiveData<Array<Acaricide>?>*//*

    @Query("SELECT * FROM acaricides ORDER BY common_name, reg_number, registrant ASC")
    fun getAcaricidesLiveData(): LiveData<List<Acaricide>>*/
}