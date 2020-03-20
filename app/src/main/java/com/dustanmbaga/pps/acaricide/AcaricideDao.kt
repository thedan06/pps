package com.dustanmbaga.pps.acaricide

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AcaricideDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
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

    /*@Insert
    fun insertAll(vararg dataEntities: Array<Acaricide>?)*/
}