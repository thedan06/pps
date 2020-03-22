package com.dustanmbaga.pps.acaricide

import android.app.Application
import androidx.lifecycle.MutableLiveData

class AcaricideRepository(application: Application) {

    val showProgress = MutableLiveData<Boolean>()

    fun changeState() {
        showProgress.value = showProgress.value != null && showProgress.value!!
    }

}