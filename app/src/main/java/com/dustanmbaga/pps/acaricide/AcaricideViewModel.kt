package com.dustanmbaga.pps.acaricide

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class AcaricideViewModel(application: Application): AndroidViewModel(application) {

    private val repository = AcaricideRepository(application)
    val showProgress: LiveData<Boolean>

    init {
        this.showProgress = repository.showProgress
    }

    fun changeState() {
        repository.changeState()
    }

}