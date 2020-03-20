package com.dustanmbaga.pps.acaricide

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class AcaricideViewModel(application: Application): AndroidViewModel(application) {
    private val acaricideRepository: AcaricideRepository
    private val allAcaricides: LiveData<List<Acaricide>>

    init {
        acaricideRepository = AcaricideRepository(application)
        allAcaricides = acaricideRepository.getAllAcaricides()
    }

    fun insertAcaricide(acaricide: Acaricide) {
        acaricideRepository.insertAcaricide(acaricide)
    }

    fun deleteAcaricide(acaricide: Acaricide) {
        acaricideRepository.deleteAcaricide(acaricide)
    }

    fun deleteAllAcaricide() {
        acaricideRepository.deleteAllAcaricide()
    }

    fun getAllAcaricides(): LiveData<List<Acaricide>> = acaricideRepository.getAllAcaricides()

    fun getAcaricideByTradeName(tradeName: String): Acaricide? {
        return acaricideRepository.getAcaricideByTradeName(tradeName)
    }

    fun getAcaricidesByCommonName(commonName: String): LiveData<Acaricide?>? {
        return acaricideRepository.getAcaricidesByCommonName(commonName)
    }

    fun getAcaricidesByRegCategory(regCategory: String): LiveData<List<Acaricide>>? {
        return acaricideRepository.getAcaricidesByRegCategory(regCategory)
    }
}