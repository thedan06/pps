package com.dustanmbaga.pps.acaricide

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class AcaricideViewModel(application: Application): AndroidViewModel(application) {

    private val repository = AcaricideRepository(application)
    val showProgress: LiveData<Boolean>
    val acaricideList: LiveData<List<AcaricideNetworkResponse>>

    init {
        this.showProgress = repository.showProgress
        this.acaricideList = repository.acaricideList
    }

    fun changeState() {
        repository.changeState()
    }

    fun getAcaricideFromApi() {
        repository.getAcaricideFromApi()
    }

}