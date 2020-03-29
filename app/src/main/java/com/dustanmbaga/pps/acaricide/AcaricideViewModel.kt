package com.dustanmbaga.pps.acaricide

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dustanmbaga.pps.util.singleArgViewModelFactory
import kotlinx.coroutines.Dispatchers


class AcaricideViewModel(private val repository: AcaricideRepository): ViewModel() {

    companion object {
        /**
         * Factory for creating [MainViewModel]
         *
         * @param arg the repository to pass to [MainViewModel]
         */
        val FACTORY = singleArgViewModelFactory(::AcaricideViewModel)
    }

    val showProgress: LiveData<Boolean>
    //val acaricideList: LiveData<List<Acaricide>>

    init {
        this.showProgress = repository.showProgress
        //this.acaricideList = repository.acaricideList
    }

    fun changeState() {
        repository.changeState()
    }

    /*fun getAcaricideFromApi() {
        repository.getAcaricideFromApi()
    }*/

    /*suspend fun refreshAcaricides() {
        repository.refreshAcaricides()
    }*/


    //----------
    val getAcaricideFromApi = liveData(Dispatchers.IO) {
        val list =  repository.getAcaricideFromApi()

        emit(list)
    }

}