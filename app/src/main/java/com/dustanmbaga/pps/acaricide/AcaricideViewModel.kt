package com.dustanmbaga.pps.acaricide

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dustanmbaga.pps.util.singleArgViewModelFactory


class AcaricideViewModel(private val repository: AcaricideRepository): ViewModel() {

    companion object {
        val FACTORY = singleArgViewModelFactory(::AcaricideViewModel)
    }

    val showProgress: LiveData<Boolean>
    val acaricideList: LiveData<List<Acaricide>>

    init {
        this.showProgress = repository.showProgress
        this.acaricideList = repository.getAcaricideFromDB()
    }

    /*fun changeState() {
        repository.changeState()
    }*/

    suspend fun refreshAcaricides() {
        repository.refreshAcaricides()
    }

}