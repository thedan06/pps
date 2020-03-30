package com.dustanmbaga.pps.acaricide

import androidx.lifecycle.LiveData
import androidx.room.*

/***
 * A database that will hold one Acaricide info.
 */
@Dao
interface AcaricideDao{

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAcaricides(vararg acaricides: Array<Acaricide>)*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAcaricide(acaricide: Acaricide)

    @Query("SELECT * FROM acaricides ORDER BY common_name, reg_number, registrant ASC")
    fun getAcaricidesLiveData(): LiveData<List<Acaricide>>

    /*@Query("SELECT * FROM acaricides WHERE common_name GLOB '*' || :commonName|| '*'")
    abstract fun searchAcaricides(commonName: String) : LiveData<List<Acaricide>>*/
}