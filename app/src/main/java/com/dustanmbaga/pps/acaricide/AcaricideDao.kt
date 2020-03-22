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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg dataEntities: Array<Acaricide>)

    @get:Query("SELECT * FROM acaricides ORDER BY common_name, reg_number, registrant ASC")
    val acaricidesLiveData: LiveData<Array<Acaricide>?>


    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAcaricide(acaricide: Acaricide)

    @Delete
    fun deleteAcaricide(acaricide: Acaricide)

    @Query("DELETE FROM acaricides")
    fun deleteAllAcaricides()

    @Query("SELECT * FROM acaricides ORDER BY common_name, reg_number, registrant ASC")
    fun getAllAcaricides(): LiveData<List<Acaricide>>

    @Query("SELECT * FROM acaricides WHERE trade_name LIKE :tradeName LIMIT 1")
    fun getAcaricideByTradeName(tradeName: String): LiveData<Acaricide?>

    @Query("SELECT * FROM acaricides WHERE trade_name LIKE '%' || :commonName || '%' LIMIT 1")
    fun getAcaricideByCommonName(commonName: String): LiveData<Acaricide?>

    @Query("SELECT * FROM acaricides WHERE trade_name LIKE :regCategory")
    fun getAcaricidesByRegCategory(regCategory: String): LiveData<List<Acaricide>>

    @Insert
    fun insertAll(vararg dataEntities: Array<Acaricide>?)*/
}